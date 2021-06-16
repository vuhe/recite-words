package top.vuhe.android.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import top.vuhe.android.R
import top.vuhe.android.databinding.ActivityWordsBinding
import top.vuhe.android.model.WordAdapter
import top.vuhe.android.model.WordViewModel

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
        setSupportActionBar(binding.toolbar)

        initUi()
        ActivityCollector.addActivity(this)
    }

    private fun initUi() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = WordAdapter(this, WordViewModel)
        binding.recycleView.adapter = adapter
        binding.addBtn.setOnClickListener {
            AddWordDialog(null).show(supportFragmentManager, "wordDialog")
        }
        WordViewModel.data.observe(this) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> SettingActivity.actionStart(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}

/**
 * ## 修改单词提示框
 */
class AddWordDialog(private val old: String?) : DialogFragment() {
    private val editText by lazy { EditText(activity) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(if (old == null) R.string.word_add else R.string.word_modify)
                .setView(editText)
                .setPositiveButton(R.string.ok) { _, _ ->
                    WordViewModel.modify(old, editText.text.toString())
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dialog?.cancel()
                }
            if (old != null) {
                editText.setText(old)
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}