package top.vuhe.android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityMainBinding

/**
 * ## 欢迎页面
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.loginBtn.setOnClickListener {
            LoginActivity.actionStart(this)
        }
        binding.registerBtn.setOnClickListener {
            RegisterActivity.actionStart(this)
        }
    }
}