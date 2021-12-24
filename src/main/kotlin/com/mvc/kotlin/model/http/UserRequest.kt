package com.mvc.kotlin.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*
/*
 @field annotation 을 쓰는 이유
    생성자 메서드 안에 annotation 을 붙이면 validation 이 동작하지 않기 때문이다.
    명시적으로 프로퍼티에 적용하겠다는 kotlin 의 annotation @field, @get, @set 을 사용 할 수 있다.
    자세히 알고 싶으면 : kotlin bean validation not working 으로 검색해 보자.
 */
data class UserRequest(
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name : String? = null,

    @field:PositiveOrZero // 0 <= 숫자 검증 (양수)
    var age : Int? = null,

    @field:Email
    var email : String? = null,

    @field:NotBlank
    var address : String? = null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    @JsonProperty("phone_number")
    var phoneNumber : String? = null,

    var createdAt: String? = null,
){
    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
    private fun isValidCreateAt(): Boolean{
        return try {
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        }catch (e: Exception){
            false
        }
    }
}
