package top.vuhe.android.entity

import java.util.concurrent.ConcurrentHashMap

private val dic = ConcurrentHashMap<String, Words>()

data class User(
    val name: String,
    val password: String,
)

fun getWordDic(name: String): Words = dic.getOrPut(name) { HashSet() }
fun putWordDic(name: String, words: Words) = dic.put(name, words)