package ru.netology.nmedia.adapter

import android.util.Log
import android.view.View
import androidx.appcompat.widget.PopupMenu
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
            like.isChecked = post.likedByMe
            like.text = post.liked.toString()

            share.isChecked = post.repostByMe
            share.text = count(post.repost)

            videoPreview.visibility = if (post.video != null) View.VISIBLE else View.GONE
            if (post.video != null) {
                videoPreview.setImageResource(R.drawable.video)
            }

            videoPreview.setOnClickListener {
                post.video?.let { videoUrl ->
                    onInteractionListener.onVideoPlay(videoUrl)
                }
            }

            like.setOnClickListener {
                onInteractionListener.onLike(post)
                Log.d("staff", "like")
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }


            root.setOnClickListener {
                Log.d("staff", "content")
            }

            avatar.setOnClickListener {
                Log.d("staff", "avatar")
            }
        }
    }
}