package com.peculiaruc.alc_mmsystem_admin.ui.programs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.peculiaruc.alc_mmsystem_admin.R

/**
* ProgramsAdapter
* */
class ProgramsAdapter(private val data: ArrayList<ProgramData>) :
    RecyclerView.Adapter<ProgramsAdapter.ProgramsAdapterViewHolder>() {

    /**
    * ProgramsAdapterViewHolder
    * */
    class ProgramsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView
        val title: TextView

        init {
            image = view.findViewById(R.id.image)
            title = view.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramsAdapterViewHolder {
        return ProgramsAdapterViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.program_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProgramsAdapterViewHolder, position: Int) {
//        holder.image.load(data[position].image) {
//            crossfade(true)
//            placeholder(R.drawable.image)
//            transformations(CircleCropTransformation())
//        }
        holder.title.text = data[position].title
        holder.itemView.setOnClickListener {
            val action = ProgramsFragmentDirections.actionProgramsFragmentToProgramsDetailsFragment()
            it.findNavController().navigate(action)
//            Toast.makeText(holder.itemView.context, "Clicked item ${data[position].id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
