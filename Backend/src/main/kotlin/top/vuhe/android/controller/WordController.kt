package top.vuhe.android.controller

import org.springframework.web.bind.annotation.*
import top.vuhe.android.entity.Response
import top.vuhe.android.entity.getWordDic
import top.vuhe.android.entity.putWordDic
import top.vuhe.android.entity.wordbook

@RestController
class WordController {
    @GetMapping("/word/{user}")
    fun getWords(@PathVariable user: String): Set<String> {
        return getWordDic(user)
    }

    @PostMapping("/word/{user}")
    fun postWords(@PathVariable user: String, @RequestBody list: List<String>): Response {
        putWordDic(user, list.toSet())
        return Response.OK
    }

    @GetMapping("/wordbook")
    fun wordsBook(): Set<String> {
        return wordbook
    }
}