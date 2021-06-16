package top.vuhe.android.model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import top.vuhe.android.R
import top.vuhe.android.activity.AddWordDialog


class WordAdapter(
    private val context: AppCompatActivity,
    private val viewModel: WordViewModel
) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordMessage: AppCompatTextView = view.findViewById(R.id.wordMessage)
        val shareBtn: MaterialButton = view.findViewById(R.id.shareBtn)
        val modifyBtn: MaterialButton = view.findViewById(R.id.modifyBtn)
        val deleteBtn: MaterialButton = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.shareBtn.setOnClickListener {
            val word = viewHolder.wordMessage.text.toString()
            val share = Intent()
            share.action = Intent.ACTION_SEND
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_SUBJECT, "word_share")
            share.putExtra(Intent.EXTRA_TEXT, word)
            val intent = Intent.createChooser(share, "单词共享: $word")
            startActivity(context, intent, null)
        }
        viewHolder.modifyBtn.setOnClickListener {
            AddWordDialog(viewHolder.wordMessage.text.toString())
                .show(context.supportFragmentManager, "wordDialog")
        }
        viewHolder.deleteBtn.setOnClickListener {
            viewModel.remove(viewHolder.wordMessage.text.toString())
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = viewModel.list[position]
        holder.wordMessage.text = word
    }

    override fun getItemCount() = viewModel.list.size
}