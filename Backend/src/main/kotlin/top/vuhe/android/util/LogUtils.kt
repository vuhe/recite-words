package top.vuhe.android.util

import top.vuhe.android.entity.User
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

typealias LogList = MutableList<String>

private val logList = LinkedList<String>()
private val f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

private fun logger(u: User, info: String) {
    val time = LocalDateTime.now(ZoneId.of("Asia/Shanghai")).format(f)
    while (logList.size > 50) {
        logList.removeFirst()
    }
    logList.add("$time ${u.name} ${info}成功")
}

fun loginLog(u: User) = logger(u, "登录")
fun logonLog(u: User) = logger(u, "注册")
fun getLogList(): LogList = logList