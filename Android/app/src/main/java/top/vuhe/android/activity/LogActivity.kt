package top.vuhe.android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import top.vuhe.android.databinding.ActivityLogBinding
import top.vuhe.android.model.LogAdapter
import top.vuhe.android.model.LogViewModel

/**
 * ## 日志页面
 */
class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding
    private lateinit var viewModel: LogViewModel

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LogActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this).get(LogViewModel::class.java)

        initUi()
    }

    private fun initUi() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        val adapter = LogAdapter(viewModel.data.value!!)
        binding.recycleView.adapter = adapter
        viewModel.data.observe(this) {
            adapter.notifyDataSetChanged()
        }
        viewModel.refresh()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}