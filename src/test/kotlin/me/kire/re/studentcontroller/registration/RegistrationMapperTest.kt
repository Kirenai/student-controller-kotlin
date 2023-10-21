package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.util.RegistrationType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers

class RegistrationMapperTest {

    private val mapper: RegistrationMapper = Mappers.getMapper(RegistrationMapper::class.java)

    @Test
    fun when_mapIn() {
        val registrationRequest = RegistrationRequest(type = RegistrationType.IN)
        val registration = mapper.mapIn(registrationRequest = registrationRequest)

        Assertions.assertNotNull(registration)
        Assertions.assertNotNull(registration.type)

        Assertions.assertEquals(registrationRequest.type, registration.type)
    }

    @Test
    fun when_mapOut() {
        val registration = Registration(
            id = "2111111111",
            type = RegistrationType.IN,
        )
        val registrationResponse = mapper.mapOut(
            registration = registration
        )

        Assertions.assertNotNull(registrationResponse)
        Assertions.assertNotNull(registrationResponse.id)
        Assertions.assertNotNull(registrationResponse.type)

        Assertions.assertEquals(registration.id, registrationResponse.id)
        Assertions.assertEquals(registration.type, registrationResponse.type)
    }

}