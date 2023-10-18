package me.kire.re.studentcontroller.student

import me.kire.re.studentcontroller.util.Constants.STUDENT_END_POINT
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest
@ContextConfiguration(classes = [StudentRouter::class, StudentHandler::class])
open class StudentRouterTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var studentRepository: StudentRepository

    @Test
    fun whenGET_findAll() {
        `when`(studentRepository.findAll())
            .thenReturn(
                Flux.just(
                    Student(id = "12341241", name = "Lucas"),
                    Student(id = "12331442", name = "Maria")
                )
            )

        client
            .get()
            .uri(STUDENT_END_POINT)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(StudentResponse::class.java)
            .hasSize(2)
    }

    @Test
    fun whenPOST_create() {
        `when`(studentRepository.save(any()))
            .thenReturn(Mono.just(Student("12341241", "Lucas")))

        client.post()
            .uri(STUDENT_END_POINT)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(Mono.just(StudentRequest(name = "Lucas")), Student::class.java)
            .exchange()
            .expectStatus().isCreated
    }

}