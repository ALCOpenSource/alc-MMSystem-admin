package com.peculiaruc.alc_mmsystem_admin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.database.Chat

import com.peculiaruc.alc_mmsystem_admin.databinding.ItemChatMeBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.ItemChatOthersBinding
import com.peculiaruc.alc_mmsystem_admin.databinding.ItemMentorChatBinding

class MentorChatAdapter(private val context: Context, private val chatList: List<Chat>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val VIEW_TYPE_ME_CHAT = 1
        private const val VIEW_TYPE_OTHERS_CHAT = 2
    }

    class FromViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(chat: Chat?){

        }

    }

    class ToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(chat: Chat?){

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ME_CHAT){
            val view = LayoutInflater.from(context).inflate(R.layout.item_chat_me,parent, false )
            FromViewHolder(view)
        }else{
            val view  = LayoutInflater.from(context).inflate(R.layout.item_chat_others,parent, false )
            ToViewHolder(view)
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = chatList[position]
        if (getItemViewType(position) == VIEW_TYPE_ME_CHAT){
            (holder as FromViewHolder).setData(current)
        }else{
            (holder as ToViewHolder).setData(current)
        }
    }

    override fun getItemCount(): Int = chatList.size

    override fun getItemViewType(position: Int): Int {

        return if(chatList[position].fromId == "1"){
            VIEW_TYPE_ME_CHAT
        }else{
            VIEW_TYPE_OTHERS_CHAT
        }


    }


}