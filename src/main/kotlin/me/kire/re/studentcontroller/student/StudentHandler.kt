package me.kire.re.studentcontroller.student

import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class StudentHandler(
    private val studentRepository: StudentRepository,
) {
    private val studentMapper = Mappers.getMapper(StudentMapper::class.java)


    fun findAll(serverRequest: ServerRequest): Mono<out ServerResponse> {
        val students = studentRepository.findAll()
            .map(studentMapper::mapOut)
        return ServerResponse.ok().body(students, StudentResponse::class.java)
    }

    fun create(serverRequest: ServerRequest): Mono<out ServerResponse> =
        serverRequest
            .bodyToMono(StudentRequest::class.java)
            .map(studentMapper::mapIn)
            .flatMap(studentRepository::save)
            .flatMap { student ->
                ServerResponse.status(HttpStatus.CREATED).bodyValue(student)
            }


}
