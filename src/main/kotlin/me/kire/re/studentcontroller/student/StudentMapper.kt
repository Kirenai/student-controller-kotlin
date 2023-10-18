package me.kire.re.studentcontroller.student

import org.mapstruct.Mapper

@Mapper
interface StudentMapper {

    fun mapIn(studentRequest: StudentRequest): Student

    fun mapOut(student: Student): StudentResponse

}