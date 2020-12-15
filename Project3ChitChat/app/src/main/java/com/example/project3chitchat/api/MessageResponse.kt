package com.example.project3chitchat.api

import com.example.project3chitchat.ChitChatItem
import com.google.gson.annotations.SerializedName

class MessageResponse {
    @SerializedName("message")
    lateinit var chitChatItems: List<ChitChatItem>
}