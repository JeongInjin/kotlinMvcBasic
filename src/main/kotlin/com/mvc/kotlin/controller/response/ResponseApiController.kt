package com.mvc.kotlin.controller.response

import com.mvc.kotlin.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {
    // 1.get 4XX
    @GetMapping("")
    fun getMapping(@RequestParam age : Int?) : ResponseEntity<String> {
        // 1. age가 null -> 400 error
        // if(age == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail")
        // 2. age < 20 -> 400 error
        // if(age < 20) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail")

        return age?.let {
            if(age < 20) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야 합니다.")
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.")
        }
    }

    // 2.post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest : UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3.put 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1.기존 데이터가ㅣ 없어서 새로 생성한다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4.delete 500
    @RequestMapping("/{id}")
    fun deleteMapping(): ResponseEntity<Nothing> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}