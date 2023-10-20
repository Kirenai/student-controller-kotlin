package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.student.StudentRepository
import me.kire.re.studentcontroller.util.Constants.STUDENT_PATH_NAME
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RegistrationHandler(
    private val registrationRepository: RegistrationRepository,
    private val studentRepository: StudentRepository
) {

    private val mapper = Mappers.getMapper(RegistrationMapper::class.java)

    fun findAllByStudentId(request: ServerRequest): Mono<out ServerResponse> {
        val studentId = request.pathVariable(STUDENT_PATH_NAME)
        val registrations = registrationRepository.findByStudentId(studentId)
            .map(mapper::mapOut)
        return ServerResponse.ok().body(registrations, RegistrationResponse::class.java)
    }

    fun create(request: ServerRequest): Mono<out ServerResponse> {
        val studentId = request.pathVariable(STUDENT_PATH_NAME)
        return studentRepository.findById(studentId)
            .flatMap { student ->
                request.bodyToMono(RegistrationRequest::class.java)
                    .map(mapper::mapIn)
                    .map { registration ->
                        student.id?.let {
                            registration.studentId = it
                        }
                        return@map registration
                    }
            }
            .flatMap(registrationRepository::save)
            .flatMap { registration ->
                ServerResponse.status(HttpStatus.CREATED).bodyValue(registration)
            }
    }

}
