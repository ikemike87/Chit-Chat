package com.example.project3chitchat

import android.widget.Button
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ChitChatFragment"

class ChitChatFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var message: EditText
    private lateinit var chitChatViewModel: ChitChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chitChatViewModel = ViewModelProvider(this).get(ChitChatViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        message = view.findViewById(R.id.edit_text_message)
        recycler = view.findViewById(R.id.message_recycler)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chitChatViewModel.chitChatItemLiveData.observe(
            viewLifecycleOwner,
            Observer { chitChatItems ->
                Log.d(TAG, "Have ChitChat items from view model $chitChatItems")
                recycler.adapter = MessageAdapter(chitChatItems)
            }
        )
    }

    private inner class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.message_text_view)
        val likeButton: Button = view.findViewById(R.id.like_button)
        val dislikeButton: Button = view.findViewById(R.id.dislike_button)
        val clientText: TextView = view.findViewById(R.id.client_text_view)
        val dateText: TextView = view.findViewById(R.id.date_text_view)

        fun bind (text: String, likes: Int, dislikes: Int, client: String, date: String) {
            message.text = text
            likeButton.text = likes.toString()
            dislikeButton.text = dislikes.toString()
            dateText.text = date
            clientText.text = client
        }

        init {
            likeButton.setOnClickListener {

            }

            dislikeButton.setOnClickListener {

            }
        }
    }

    private inner class MessageAdapter(private val chitChatItems: List<ChitChatItem>) : RecyclerView.Adapter<MessageViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
            val view = layoutInflater.inflate(R.layout.list_item_message, parent, false)
            return MessageViewHolder(view)
        }

        override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
            val chitChatItem = chitChatItems[position]
            holder.bind(chitChatItem.message, chitChatItem.likes, chitChatItem.dislikes, chitChatItem.client, chitChatItem.date)
        }

        override fun getItemCount(): Int {
            return 20
        }
    }
}

