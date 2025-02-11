package ru.netology.nmedia.dto

data class Post (
    val id:Long,
    val author:String,
    val published:String,
    val content:String,
    val liked:Int,
    val likedByMe:Boolean,
    val repost:Int,
    val repostByMe:Boolean
)