package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import top.vuhe.android.databinding.ActivityWordsBinding

/**
 * ## 单词页面
 */
class WordsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWordsBinding

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, WordsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}