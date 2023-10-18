package me.kire.re.studentcontroller.student

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : ReactiveMongoRepository<Student, String>