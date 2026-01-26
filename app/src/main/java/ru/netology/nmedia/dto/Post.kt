package ru.netology.nmedia.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post (
    val id:Long,
    val author:String,
    val published:String,
    val content:String,
    val liked:Int,
    val likedByMe:Boolean,
    val repost:Int,
    val repostByMe:Boolean,
    val video: String? = null
) : Parcelable