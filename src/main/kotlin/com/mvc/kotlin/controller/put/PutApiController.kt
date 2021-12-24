package com.mvc.kotlin.controller.put

import com.mvc.kotlin.model.http.Result
import com.mvc.kotlin.model.http.UserRequest
import com.mvc.kotlin.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping() : String{
        return "put-mapping"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObejct(@Valid @RequestBody userRequest : UserRequest, bindingResult: BindingResult) : ResponseEntity<String> {

        if(bindingResult.hasErrors()) {
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field + " : " + message + "\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        return ResponseEntity.ok("")
        // 0.Response
//        return UserResponse().apply {
//            // 1.result
//            this.result = Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//        }.apply {
//            // 2.desciption
//            this.description = "!@%@#$@!#%!#%"
//        }.apply {
//            // 3.user mutable list
//            var userList = mutableListOf<UserRequest>()
//
//            userList.add(userRequest)
//
//            userList.add(UserRequest().apply {
//                this.name = "a"
//                this.age = 20
//                this.email = "a address"
//                this.phoneNumber = "010-1234-1234"
//            })
//            userList.add(UserRequest().apply {
//                this.name = "b"
//                this.age = 22
//                this.email = "b address"
//                this.phoneNumber = "010-4321-4321"
//            })
//
//            this.user = userList
//        }
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping() : String{
        return "request-mapping : put method"
    }
}