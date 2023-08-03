package com.example.miniwhatsapp

class Message {
    var message : String? = null
    var senderID : String? = null

    constructor()

    constructor(message: String?, senderId: String?) {
        this.message = message
        this.senderID = senderId
    }
}