package ru.netology.nmedia.dto

data class Post (
    val id:Long,
    val author:String,
    val published:String,
    val content:String,
    val liked:Int = 0,
    val likedByMe:Boolean = false,
    val repost:Int = 0,
    val repostByMe:Boolean = false
)