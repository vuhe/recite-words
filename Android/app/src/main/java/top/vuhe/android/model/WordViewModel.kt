package top.vuhe.android.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel : ViewModel() {
    val list: List<String>
        get() = data.value!!
    val data: LiveData<MutableList<String>>
        get() = _data
    private val _data = MutableLiveData<MutableList<String>>()
    private val set = HashSet<String>()

    init {
        _data.value = ArrayList()
    }

    fun modify(oldWord: String?, newWord: String) {
        _data.value?.let {
            val i = if (oldWord == null) -1 else it.indexOf(oldWord)
            if (i == -1) {
                it.add(newWord)
            } else {
                it[i] = newWord
            }
            _data.value = it
        }
    }

    fun addAll(list: Collection<String>) {
        _data.value?.let { l ->
            list.forEach {
                if (set.contains(it).not()) {
                    set.add(it)
                    l.add(it)
                }
            }
            _data.value = l
        }
    }

    fun remove(word: String) {
        _data.value?.let {
            it.remove(word)
            _data.value = it
        }
    }
}