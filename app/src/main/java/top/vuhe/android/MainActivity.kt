package top.vuhe.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import top.vuhe.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val USER = "vuhe"
    private val PASSWORD = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 设备 button 触发功能
        binding.loginBtn.setOnClickListener {
            // 获取登录数据
            val account = binding.accountEdit.text.toString()
            val password = binding.passwordEdit.text.toString()

            // 登录验证
            val info = if (account == USER && password == PASSWORD) {
                "登录成功"
            } else {
                "登录失败"
            }

            // 信息提示
            Toast.makeText(this, info, Toast.LENGTH_LONG).show()
        }
    }
}