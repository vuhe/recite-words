package top.vuhe.android.entity

data class User(
    val name: String,
    val password: String
) {
    companion object {
        val emptyUser = User("", "")
    }
}