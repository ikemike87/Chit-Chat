package com.example.project3chitchat

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ChitChatViewModel : ViewModel() {
    val chitChatItemLiveData: LiveData<List<ChitChatItem>>

    init{
        chitChatItemLiveData = ChitChatFetch().fetchContents()
    }
}