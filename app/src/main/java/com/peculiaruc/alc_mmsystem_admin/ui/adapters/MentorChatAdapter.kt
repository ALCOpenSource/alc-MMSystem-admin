package com.peculiaruc.alc_mmsystem_admin.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peculiaruc.alc_mmsystem_admin.R
import com.peculiaruc.alc_mmsystem_admin.database.Chat

/**
 * This is the adapter for the chat messages
 * in the chat screen
 */

class MentorChatAdapter(private val context: Context, private val chatList: List<Chat>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val VIEW_TYPE_ME_CHAT = 1
        private const val VIEW_TYPE_OTHERS_CHAT = 2
    }

    /**
     * The view holder to determine view of the messages entered by the user
      */
    class FromViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * set the data from the server in the appropriate view
         */
        fun setData(chat: Chat?){
            TODO("Bind views here")
        }

    }

    /**
     * The view holder to determine view of the messages received by the user
     */

    class ToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(chat: Chat?){
            TODO("Bind views here")
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
        val current = chatList!![position]
        if (getItemViewType(position) == VIEW_TYPE_ME_CHAT){
            (holder as FromViewHolder).setData(current)
        }else{
            (holder as ToViewHolder).setData(current)
        }
    }

    override fun getItemCount(): Int = chatList?.size ?: 0

    override fun getItemViewType(position: Int): Int {

        return if(chatList!![position].fromId == "1"){
            VIEW_TYPE_ME_CHAT
        }else{
            VIEW_TYPE_OTHERS_CHAT
        }


    }


}