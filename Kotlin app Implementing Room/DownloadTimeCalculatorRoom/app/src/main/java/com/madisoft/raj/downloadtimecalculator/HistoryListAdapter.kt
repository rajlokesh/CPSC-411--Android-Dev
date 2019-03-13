package com.madisoft.raj.downloadtimecalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryListAdapter internal constructor(
context: Context
) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var historys = emptyList<History>() // Cached copy of historys

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historyItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = historys[position]
        holder.historyItemView.text = current.history
    }

    internal fun setHistorys(historys: List<History>) {
        this.historys = historys
        notifyDataSetChanged()
    }

    override fun getItemCount() = historys.size
}