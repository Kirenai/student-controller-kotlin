package me.kire.re.studentcontroller.student

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class StudentMapperTest {

    private val mapper: StudentMapper = Mappers.getMapper(StudentMapper::class.java)

    @Test
    fun when_mapIn() {
        val studentRequest = StudentRequest("Lucas")
        val student = mapper.mapIn(studentRequest = studentRequest)

        Assertions.assertNotNull(student)
        Assertions.assertNotNull(student.name)

        Assertions.assertEquals(studentRequest.name, student.name)
    }

    @Test
    fun when_mapOut() {
        val student = Student(id = "1222222222", name = "Lucas")
        val studentResponse = mapper.mapOut(student = student)

        Assertions.assertNotNull(studentResponse.id)
        Assertions.assertNotNull(studentResponse.name)

        Assertions.assertEquals(student.id, studentResponse.id)
        Assertions.assertEquals(student.name, studentResponse.name)
    }

}