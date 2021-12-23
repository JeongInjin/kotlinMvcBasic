package com.mvc.kotlin.controller.post

import com.mvc.kotlin.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping() : String{
        return "post-mapping"
    }

    @PostMapping("/post-mapping/object")
    fun postmappingObject(@RequestBody userRequest : UserRequest) : UserRequest{
        return userRequest
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping() : String{
        return "request-post-mapping"
    }


}