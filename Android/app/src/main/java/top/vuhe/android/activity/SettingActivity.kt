package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import top.vuhe.android.databinding.ActivitySettingBinding

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

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        // TODO 获取用户信息填入
        userInfo = "当前用户: " + ""
        binding.logBtn.setOnClickListener { LogActivity.actionStart(this) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}