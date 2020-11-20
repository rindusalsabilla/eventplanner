package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity

class EventAdapter : RecyclerView.Adapter<EventAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    private var listEvent: List<EventEntity> = listOf()

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(view.context).inflate(R.layout.event_item, view, false)
        return ListViewHolder(itemView)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: EventEntity, position: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){

        var currentEvent = listEvent.get(position)
        holder.event.setText(currentEvent.event)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listEvent[holder.adapterPosition], position)
        }
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    fun setContacts(contacts: List<EventEntity>) {
        listEvent = contacts
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var event: TextView = itemView.findViewById(R.id.event)
    }
}