package com.mvc.kotlin.controller.exception

import com.mvc.kotlin.model.http.Error
import com.mvc.kotlin.model.http.ErrorResponse
import com.mvc.kotlin.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@Validated
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 8)
        @RequestParam name: String,

        @Min(10)
        @RequestParam age: Int,
    ): String{
        println("name = ${name}, age = ${age}")
        return "name = ${name}, age = ${age}"
    }

    @PostMapping("")
    //res
//    fun post(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult){ //userRequest valid 결과를 받을 시
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        var errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach{
            val error = Error().apply {
                this.field = (it as FieldError).field
                this.message = it.defaultMessage
                this.value = it.rejectedValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun ConstraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse>{
        val errors = mutableListOf<Error>()
        e.constraintViolations.forEach{
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @GetMapping("/hello")
    fun hello() {
        throw RuntimeException("강제 exception 발생")
    }

    @GetMapping("/hello2")
    fun hello2(){
        val list = mutableListOf<String>()

        val temp = list[0]
    }

    @GetMapping("/hello-test")
    fun helloTest(): String{
        var list = mutableListOf<String>()

        return "hello-test"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index ERROR")
    }
}