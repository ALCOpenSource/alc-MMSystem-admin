package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R

/**
 * ProgramDetailsAdapter
 * */
class ProgramDetailsAdapter(private val data: ArrayList<ProgramData>) :
    RecyclerView.Adapter<ProgramDetailsAdapter.ProgramsAdapterViewHolder>() {

    /**
     * ProgramsAdapterViewHolder
     * */
    class ProgramsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val count: TextView
        val description: TextView
        val button: Button

        init {
            count = view.findViewById(R.id.count)
            description = view.findViewById(R.id.description)
            button = view.findViewById(R.id.button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramsAdapterViewHolder {
        return ProgramsAdapterViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.program_details_more_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProgramsAdapterViewHolder, position: Int) {
        holder.count.text = "67"
        holder.description.text = data[position].title
        holder.button.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked item ${data[position].id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
