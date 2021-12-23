package com.mvc.kotlin.controller.put

import com.mvc.kotlin.model.http.Result
import com.mvc.kotlin.model.http.UserRequest
import com.mvc.kotlin.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping() : String{
        return "put-mapping"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObejct(@RequestBody userRequest : UserRequest) : UserResponse{
        // 0.Response
        return UserResponse().apply {
            // 1.result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2.desciption
            this.description = "!@%@#$@!#%!#%"
        }.apply {
            // 3.user mutable list
            var userList = mutableListOf<UserRequest>()

            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 20
                this.email = "a address"
                this.phoneNumber = "010-1234-1234"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 22
                this.email = "b address"
                this.phoneNumber = "010-4321-4321"
            })

            this.userRequest = userList
        }
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping() : String{
        return "request-mapping : put method"
    }
}