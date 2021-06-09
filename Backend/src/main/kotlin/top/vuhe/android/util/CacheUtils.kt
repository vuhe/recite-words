package top.vuhe.android.util

import top.vuhe.android.entity.User
import java.util.concurrent.ConcurrentHashMap

object CacheUtils {
    private val cache = ConcurrentHashMap<String, Cache>()
    private const val maxNumber = 20
    private const val oneDay: Long = 24 * 60 * 60 * 1000

    @Synchronized
    operator fun get(key: String): User? {
        val c = cache[key]
        c?.let {
            if (c.time < System.currentTimeMillis()) {
                cache.remove(key)
                return null
            }
        }
        return c?.user
    }

    @Synchronized
    operator fun set(key: String, value: User) {
        if (cache.size > maxNumber) {
            cache.iterator().let {
                if (it.next().value.time < System.currentTimeMillis()) {
                    it.remove()
                }
            }
        }
        cache[key] = Cache(value, System.currentTimeMillis() + oneDay)
    }

    private data class Cache(
        val user: User,
        val time: Long
    )
}