package top.vuhe.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val input = resources.openRawResource(R.raw.std)
            // 登录验证
            val info = if (xmlCheckInfo(account, password, input)) "成功" else "失败"
            prefs.edit().apply {
                putString("account", account)
                putString("password", password)
                apply()
            }
            // 信息提示
            Toast.makeText(this, "xml 验证: 登录$info", Toast.LENGTH_LONG).show()
        }

        // json
        binding.jsonLoginBtn.setOnClickListener {
            val input = resources.openRawResource(R.raw.std2)
            // 登录验证
            val info = if (jsonCheckInfo(account, password, input)) "成功" else "失败"
            prefs.edit().apply {
                putString("account", account)
                putString("password", password)
                apply()
            }
            // 信息提示
            Toast.makeText(this, "json 验证: 登录$info", Toast.LENGTH_LONG).show()
        }
    }
}