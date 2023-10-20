package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.util.RegistrationType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "registrations")
data class Registration(
    @Id
    val id: String? = null,
    val type: RegistrationType,
    var studentId: String? = null
)
