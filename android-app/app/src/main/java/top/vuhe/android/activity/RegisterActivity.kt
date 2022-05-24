package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.coroutines.*
import top.vuhe.android.databinding.ActivityRegisterBinding
import top.vuhe.android.entity.User
import top.vuhe.android.model.UserViewModel
import top.vuhe.android.network.UserApi

/**
 * ## 注册页面
 */
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var username
        get() = binding.usernameEdit.text.toString()
        set(value) = binding.usernameEdit.setText(value)
    private var password
        get() = binding.passwordEdit.text.toString()
        set(value) = binding.passwordEdit.setText(value)

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        binding.loginBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val result = UserApi.service.register(User(username, password))
                if (result.code == 200) {
                    registerOk()
                } else {
                    registerFiled()
                }
            }
        }
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

    private fun registerOk() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        UserViewModel.updateUser(User(username, password))
        WordsActivity.actionStart(this)
    }

    private fun registerFiled() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show()
    }
}