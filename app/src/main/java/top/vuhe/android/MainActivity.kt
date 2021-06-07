package top.vuhe.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import top.vuhe.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var name
        get() = binding.nameEdit.text.toString()
        set(value) = binding.nameEdit.setText(value)

    private var phone
        get() = binding.phoneEdit.text.toString()
        set(value) = binding.phoneEdit.setText(value)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设定绑定视图
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 设置根视图
        setContentView(binding.root)
        // 初始化数据库
        initSQLiteHelper(this)

        binding.insertBtn.setOnClickListener {
            val ans = if (insertData(Contact(name, phone))) "成功" else "失败"
            Toast.makeText(this, "插入$ans", Toast.LENGTH_SHORT).show()
        }
        binding.searchBtn.setOnClickListener {
            val ans = if(searchData(Contact(name, phone))) "已" else "未"
            Toast.makeText(this, "${ans}查询到数据存在", Toast.LENGTH_SHORT).show()
        }
        binding.deleteBtn.setOnClickListener {
            deleteData(Contact(name, phone))
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
        }
        binding.updateBtn.setOnClickListener {
            updateData(Contact(name, phone))
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show()
        }
    }
}