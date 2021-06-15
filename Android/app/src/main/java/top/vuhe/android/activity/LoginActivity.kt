package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import top.vuhe.android.databinding.ActivityLoginBinding
import top.vuhe.android.entity.User
import top.vuhe.android.model.UserViewModel
import top.vuhe.android.network.UserApi

/**
 * ## 登录页面
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var username
        get() = binding.usernameEdit.text.toString()
        set(value) = binding.usernameEdit.setText(value)
    private var password
        get() = binding.passwordEdit.text.toString()
        set(value) = binding.passwordEdit.setText(value)

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        binding.loginBtn.setOnClickListener {
            MainScope().launch {
                val result = UserApi.service.login(User(username, password))
                if (result.code == 200) {
                    loginOk()
                } else {
                    loginFiled()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loginOk() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        UserViewModel.updateUser(User(username, password))
        WordsActivity.actionStart(this)
    }

    private fun loginFiled() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show()
    }
}