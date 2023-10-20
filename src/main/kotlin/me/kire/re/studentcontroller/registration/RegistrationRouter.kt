package me.kire.re.studentcontroller.registration

import me.kire.re.studentcontroller.util.Constants.REGISTRATION_END_POINT
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class RegistrationRouter(
    private val handler: RegistrationHandler
) {

    @Bean
    fun routerRegistration() = router {
        GET(REGISTRATION_END_POINT.plus("/search/student/{studentId}"), handler::findAllByStudentId)
        POST(REGISTRATION_END_POINT.plus("/student/{studentId}"), handler::create)
    }

}