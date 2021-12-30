package app.sample.messages

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/")
class HelloController(val service: MockService) {
    @GetMapping
    @ResponseBody
    fun returnHello(): String{
        service.printName()
        return "Hello"
    }

    @GetMapping("/welcome")
    fun welcome(model: Model){
        model["message"] = "Hello, Welcome to Spring Boot!"
    }
}