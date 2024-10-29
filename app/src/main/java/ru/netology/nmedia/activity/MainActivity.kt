package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.ViewModel.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.count

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.repostByMe) {
                    share.setImageResource(R.drawable.ic_share_24)
                }
                likeCount?.text = post.liked.toString()
                likeCount?.text = count(post.liked)
                shareCount?.text = count(post.repost)


                root.setOnClickListener {
                    Log.d("staff", "content")
                }

                avatar.setOnClickListener {
                    Log.d("staff", "avatar")
                }
            }
        }

        binding.like?.setOnClickListener {
            viewModel.like()
            Log.d("staff", "like")
            }
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }
}
