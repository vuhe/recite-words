package top.vuhe.android.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import top.vuhe.android.entity.User

object UserViewModel : ViewModel() {
    val user
        get() = currUser.value!!
    private val currUser = MutableLiveData<User>()

    init {
        currUser.value = User.emptyUser
    }

    fun checkUser(checker: User): Boolean {
        return checker != User.emptyUser && checker == currUser.value
    }

    fun updateUser(user: User) {
        if (user == User.emptyUser || (user.name.isEmpty())) return
        currUser.value = user
    }
}