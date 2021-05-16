package top.vuhe.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityBloodBinding

class BloodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBloodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityBloodBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 设置按钮
        binding.addBloodBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("blood", 100)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}