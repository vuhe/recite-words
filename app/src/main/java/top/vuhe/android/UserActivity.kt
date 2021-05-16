package top.vuhe.android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private var blood = 0
    private val returnFlag = 1

    companion object {
        fun actionStart(context: Context, user: String) {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("username", user)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityUserBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)

        // 获取数据
        val username = intent.getStringExtra("username")

        // 设置视图数据
        binding.usernameText.text = username
        binding.bloodText.text = blood.toString()
        binding.addBloodBtn.setOnClickListener {
            val intent = Intent(this, BloodActivity::class.java)
            startActivityForResult(intent, returnFlag)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            returnFlag -> if (resultCode == RESULT_OK) {
                blood += data?.getIntExtra("blood", 0)!!
                binding.bloodText.text = blood.toString()
            }
        }
    }
}