package top.vuhe.android.controller

import org.springframework.web.bind.annotation.*
import top.vuhe.android.entity.Response
import top.vuhe.android.entity.User
import top.vuhe.android.util.*

@RestController
class UserController {
    @PostMapping("/login")
    fun login(@RequestBody user: User): Response {
        val u = CacheUtils[user.name]
        return if (u != null && u.password == user.password) {
            loginLog(user)
            Response.OK
        } else Response.ERROR
    }

    @PostMapping("/logon")
    fun logon(@RequestBody user: User):Response {
        CacheUtils[user.name] = user
        logonLog(user)
        return Response.OK
    }

    @GetMapping("/log")
    fun logInfo(): LogList {
        return getLogList()
    }
}