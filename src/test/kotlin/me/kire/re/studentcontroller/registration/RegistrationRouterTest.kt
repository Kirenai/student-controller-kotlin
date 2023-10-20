package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.student.Student
import me.kire.re.studentcontroller.student.StudentRepository
import me.kire.re.studentcontroller.util.Constants.REGISTRATION_END_POINT
import me.kire.re.studentcontroller.util.RegistrationType
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest
@ContextConfiguration(classes = [RegistrationRouter::class, RegistrationHandler::class])
class RegistrationRouterTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var registrationRepository: RegistrationRepository

    @MockBean
    lateinit var studentRepository: StudentRepository

    @Test
    fun whenGET_findAllByStudentId() {
        `when`(registrationRepository.findByStudentId(anyString()))
            .thenReturn(
                Flux.just(
                    Registration(id = "12321412", type = RegistrationType.IN, studentId = "122222222"),
                    Registration(id = "12321413", type = RegistrationType.OUT, studentId = "122222222")
                )
            )

        client
            .get()
            .uri(REGISTRATION_END_POINT.plus("/search/student/122222222"))
            .exchange()
            .expectStatus().isOk
            .expectBodyList(RegistrationResponse::class.java)
            .hasSize(2)
    }

    @Test
    fun whenPOST_create() {
        `when`(this.studentRepository.findById(anyString()))
            .thenReturn(Mono.just(Student(id = "122222222", name = "Lucas")))
        `when`(this.registrationRepository.save(any()))
            .thenReturn(Mono.just(Registration(id = "12321412", type = RegistrationType.IN, studentId = "122222222")))

        client
            .post()
            .uri(REGISTRATION_END_POINT.plus("/student/122222222"))
            .body(Mono.just(RegistrationRequest(type = RegistrationType.IN)), RegistrationRequest::class.java)
            .exchange()
            .expectStatus().isCreated
    }

}
