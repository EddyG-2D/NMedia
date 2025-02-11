package ru.netology.nmedia.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.count

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            if (post.repostByMe) {
                share.setImageResource(R.drawable.ic_share_24)
            }

            likeCount?.text = count(post.liked)
            shareCount?.text = count(post.repost)

            root.setOnClickListener {
                Log.d("staff", "content")
            }

            avatar.setOnClickListener {
                Log.d("staff", "avatar")
            }

            like.setOnClickListener {
                onInteractionListener.onLike(post)
                Log.d("staff", "like")
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }
        }
    }
}