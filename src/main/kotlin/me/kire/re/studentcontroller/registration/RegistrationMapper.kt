package me.kire.re.studentcontroller.registration

import org.mapstruct.Mapper

@Mapper
interface RegistrationMapper {

    fun mapIn(registrationRequest: RegistrationRequest): Registration

    fun mapOut(registration: Registration): RegistrationResponse

}