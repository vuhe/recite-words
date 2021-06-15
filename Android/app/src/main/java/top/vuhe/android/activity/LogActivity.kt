package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityLogBinding

/**
 * ## 日志页面
 */
class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LogActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}