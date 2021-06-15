package top.vuhe.android.model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import top.vuhe.android.R

class LogAdapter(private val logs: List<String>) :
    RecyclerView.Adapter<LogAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logMessage: AppCompatTextView = view.findViewById(R.id.logMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_log, parent, false)
        Log.e("  ", logs.toString())
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = logs[position]
        holder.logMessage.text = log
    }

    override fun getItemCount() = logs.size
}