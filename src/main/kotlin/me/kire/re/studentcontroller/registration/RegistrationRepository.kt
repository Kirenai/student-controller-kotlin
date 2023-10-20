package me.kire.re.studentcontroller.registration

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface RegistrationRepository : ReactiveMongoRepository<Registration, String> {

    fun findByStudentId(studentId: String): Flux<Registration>

}
