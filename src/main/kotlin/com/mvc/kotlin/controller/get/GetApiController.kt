package com.mvc.kotlin.controller.get

import com.mvc.kotlin.model.http.UserRequest
import org.springframework.web.bind.annotation.*

/* 코틀린에서는 query parameter 에 하이픈을 넣을 수 없어, variable, name 속성을 통하여 받는다 ex) value = "phone-number"
    또는 Map 형태로 받으면 된다.*/
//RestController 선언시 return type  => Object 일 경우 자동으로 json 형태로 자동 변환하여 반환한다.
@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello() : String{
        return "hello kotlin"
    }

    @GetMapping(path = ["/hello2", "/hello3"])
    fun hello2() : String{
        return "hello kotlin 2 or 3"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping() : String{
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}")
    fun pathVariable(@PathVariable name: String): String{
        return "hello ${name}"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String{
        return "${name} ${age}"
    }

    // pathVariable 변수 이름이 같지않을 경우 value 속성과, name 속성을 이용하게 값을 맞춰 줄 수 있다.
    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value= "name") _name: String, @PathVariable(name = "age") age: Int): String{
        val name = "kotlin"
        return "${_name} ${age}"
    }

    // query parameter @RequestParam 또한 value, name 옵션을 지원한다.
    @GetMapping("/get-mapping/query-param")
    fun queryParam(@RequestParam name: String, age: Int): String{
        return "${name} ${age}"
    }

    //Object query parameter
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest : UserRequest) : UserRequest{
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map : Map<String, Any>): Map<String, Any> {
        var phoneNumber = map.get("phone-number")
        println(phoneNumber)

        return map
    }
}