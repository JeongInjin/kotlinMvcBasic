package com.mvc.kotlin.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.mvc.kotlin.annotation.StringFormatDateTime
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

    @field: StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt: String? = null,
)
/*
@Size 문자 길이 측정 - Int Type 불가
@NotNull null 불가
@NotEmpty null, "" 불가
@NotBlank null, "", " " 불가
@Past 과거날짜
@PastOrPresent 오늘이거나 과거 날짜
@Future 미래 날짜
@FutureOrPresent 오늘이거나 미래 날짜
@Pattern 정규식 적용
@Max 최대값
@Min 최소값
@AssertTrue / False - 별도 Logic 적용
@Valid 해당 object validation 실행
...
 */