package top.vuhe.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var account
        get() = binding.accountEdit.text.toString()
        set(value) = binding.accountEdit.setText(value)

    private var password
        get() = binding.passwordEdit.text.toString()
        set(value) = binding.passwordEdit.setText(value)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 记住密码恢复
        val prefs = getPreferences(Context.MODE_PRIVATE)
        account = prefs.getString("account", "")!!
        password = prefs.getString("password", "")!!

        // xml
        binding.xmlLoginBtn.setOnClickListener {
            WeatherActivity.actionStart(this, "xml")
        }

        // json
        binding.jsonLoginBtn.setOnClickListener {
            WeatherActivity.actionStart(this, "json")
        }
    }
}