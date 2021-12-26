package com.mvc.kotlin.controller.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mvc.kotlin.model.http.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun hello_테스트(){
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello-test")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("hello-test")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun hello_테스트_import_static(){
        mockMvc.perform(
            get("/api/exception/hello-test")
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("hello-test")
        ).andDo(print())
    }

    @Test
    fun get_테스트(){
        var queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "jeong")
        queryParams.add("age", "20")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("name = jeong, age = 20")
        ).andDo(print())
    }

    @Test
    fun get_실패_테스트(){
        var queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "jeong")
        queryParams.add("age", "8")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].message").value("must be greater than or equal to 10")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("8")
        ).andDo(print())
    }

    @Test
    fun post_테스트(){

        val userRequest = UserRequest().apply {
            this.name = "jeong"
            this.age = 10
            this.phoneNumber = "010-1111-2222"
            this.address = "seoul"
            this.email = "mail@gmail.com"
            this.createdAt = "2222-02-02 22:22:22"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("\$.name").value("jeong")
        ).andExpect(
            jsonPath("\$.age").value("10")
        ).andExpect(
            jsonPath("\$.phone_number").value("010-1111-2222")
        ).andExpect(
            jsonPath("\$.address").value("seoul")
        ).andExpect(
            jsonPath("\$.email").value("mail@gmail.com")
        ).andExpect(
            jsonPath("\$.createdAt").value("2222-02-02 22:22:22")
        ).andDo(print())
    }

    @Test
    fun post_실패_테스트(){

        val userRequest = UserRequest().apply {
            this.name = "jeong"
            this.age = -1
            this.phoneNumber = "010-1111-2222"
            this.address = "seoul"
            this.email = "mail@gmail.com"
            this.createdAt = "2222-02-02 22:22:22"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        println(json)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            status().isBadRequest
        ).andDo(print())
    }
}