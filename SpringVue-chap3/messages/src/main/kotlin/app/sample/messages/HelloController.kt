package app.sample.messages

import app.sample.messages.aop.SecurityCheck
import app.sample.messages.aop.SecurityChecker
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/")
class HelloController(private var beanTest: BeanTest, private val securityChecker: SecurityChecker) {
    @GetMapping
    @ResponseBody
    @SecurityCheck
    fun returnHello(): String{
        beanTest.printName()
        return "Hello"
    }

    @GetMapping("/welcome")
    fun welcome(model: Model){
        model["message"] = "Hello, Welcome to Spring Boot!"
    }
}