package me.kire.re.studentcontroller.student

import me.kire.re.studentcontroller.util.Constants.STUDENT_END_POINT
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class StudentRouter(
    private val handler: StudentHandler
) {

    @Bean
    fun route() = router {
        GET(STUDENT_END_POINT, handler::findAll)
        POST(STUDENT_END_POINT, handler::create)
    }

}