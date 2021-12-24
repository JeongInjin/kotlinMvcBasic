package com.mvc.kotlin.controller.page

import com.mvc.kotlin.model.http.UserRequest
import com.mvc.kotlin.model.http.UserResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String{
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/test")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name = "jeong"
        }
        //return "main.html"
    }
}