package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import top.vuhe.android.databinding.ActivitySettingBinding
import top.vuhe.android.model.UserViewModel
import top.vuhe.android.model.WordViewModel
import top.vuhe.android.network.WordApi

/**
 * ## 设置页面
 */
class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private var userInfo: String
        get() = binding.userInfo.text.toString()
        set(value) {
            binding.userInfo.text = value
        }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initUi()
        ActivityCollector.addActivity(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    private fun initUi() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        userInfo = "当前用户: " + UserViewModel.user.name
        binding.uploadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val username = UserViewModel.user.name
                val wordbook = WordViewModel.list
                WordApi.service.upload(username, wordbook)
                toast("上传成功")
            }
        }
        binding.restoreBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val username = UserViewModel.user.name
                val wordbook = WordApi.service.download(username)
                WordViewModel.addAll(wordbook)
                toast("恢复成功")
            }
        }
        binding.downloadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val username = UserViewModel.user.name
                val wordbook = WordApi.service.getWordbook()
                WordViewModel.addAll(wordbook)
                toast("下载成功")
            }
        }
        binding.logBtn.setOnClickListener { LogActivity.actionStart(this) }
        binding.logoutBtn.setOnClickListener { ActivityCollector.logout() }
    }

    private fun toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}