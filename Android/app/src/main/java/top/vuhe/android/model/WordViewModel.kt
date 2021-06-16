package top.vuhe.android.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object WordViewModel : ViewModel() {
    val list: List<String>
        get() = data.value!!
    val data: LiveData<MutableList<String>>
        get() = words
    private val words = MutableLiveData<MutableList<String>>()
    private val set = HashSet<String>()

    init {
        words.value = ArrayList()
    }

    fun modify(oldWord: String?, newWord: String) {
        words.value?.let {
            val i = if (oldWord == null) -1 else it.indexOf(oldWord)
            if (i == -1) {
                it.add(newWord)
                set.add(newWord)
            } else {
                it[i] = newWord
                set.remove(oldWord)
                set.add(newWord)
            }
            words.value = it
        }
    }

    fun addAll(list: Collection<String>) {
        words.value?.let { l ->
            list.forEach {
                if (set.contains(it).not()) {
                    set.add(it)
                    l.add(it)
                }
            }
            words.value = l
        }
    }

    fun remove(word: String) {
        words.value?.let {
            it.remove(word)
            set.remove(word)
            words.value = it
        }
    }

    fun logout() {
        words.value?.let {
            it.clear()
            set.clear()
            words.value = it
        }
    }
}