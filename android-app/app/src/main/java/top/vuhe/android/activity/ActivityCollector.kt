package top.vuhe.android.activity

import androidx.appcompat.app.AppCompatActivity
import top.vuhe.android.model.UserViewModel
import top.vuhe.android.model.WordViewModel

object ActivityCollector {
    private val activities = ArrayList<AppCompatActivity>()

    fun addActivity(activity: AppCompatActivity) {
        activities.add(activity)
    }

    fun removeActivity(activity: AppCompatActivity) {
        activities.remove(activity)
    }

    fun logout() {
        for (activity in activities) {
            if (activity.isFinishing.not()) {
                activity.finish()
            }
        }
        UserViewModel.logout()
        WordViewModel.logout()
        activities.clear()
    }
}