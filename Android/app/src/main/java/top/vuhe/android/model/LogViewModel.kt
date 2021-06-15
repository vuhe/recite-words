package top.vuhe.android.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import top.vuhe.android.network.UserApi

class LogViewModel : ViewModel() {
    val data: LiveData<MutableList<String>>
        get() = logs
    private val logs = MutableLiveData<MutableList<String>>()

    init {
        logs.value = ArrayList()
    }

    fun refresh() = viewModelScope.launch(Dispatchers.Main) {
        val log = UserApi.service.userLog()
        logs.value?.let {
            it.clear()
            it.addAll(log)
            logs.postValue(it)
        }
    }
}