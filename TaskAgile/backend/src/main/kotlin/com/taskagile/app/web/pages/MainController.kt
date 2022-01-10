package com.taskagile.app.web.pages

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping(value = ["/", "/login"])
    fun entry() = "index.html"

}