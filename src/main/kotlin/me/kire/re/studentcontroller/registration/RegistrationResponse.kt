package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.util.RegistrationType

data class RegistrationResponse(
    val id: String,
    val type: RegistrationType
)
