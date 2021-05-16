package top.vuhe.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 设备 button 触发功能
        binding.registeredBtn.setOnClickListener {
            // 获取登录数据
            val account = binding.accountEdit.text.toString()

            // 跳转第二页面
            UserActivity.actionStart(this, account)
        }
    }
}