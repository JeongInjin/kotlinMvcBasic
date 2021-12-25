package com.mvc.kotlin.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(@RequestParam name: String, age: Int): String {
        return "${name} ${age}"
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingpath(@PathVariable(name = "name") _name: String, @PathVariable(name = "age") _age: Int): String {
        return "${_name} ${_age}"
    }
}