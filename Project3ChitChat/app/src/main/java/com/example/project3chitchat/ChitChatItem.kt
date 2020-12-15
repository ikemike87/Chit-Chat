package com.example.project3chitchat

data class ChitChatItem(
        var client: String = "",
        var date: String = "",
        var dislikes: Int,
        var likes: Int,
        var message: String = ""
)