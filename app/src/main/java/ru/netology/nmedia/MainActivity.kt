package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.count

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = post.liked.toString()
            shareCount?.text = post.repostByMe.toString()

            root.setOnClickListener {
                Log.d("staff", "content")
            }

            avatar.setOnClickListener {
                Log.d("staff", "avatar")
            }


            like?.setOnClickListener {
                Log.d("staff", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.liked++ else post.liked--
                likeCount?.text = post.liked.toString()
                likeCount?.text = count(post.liked)

            }
            shareCount.text= post.repost.toString()
            if (post.repostByMe){
                share.setImageResource(R.drawable.ic_share_24)
            }
            share.setOnClickListener {
                post.repostByMe = !post.repostByMe
                post.repost++
                shareCount?.text= post.repost.toString()
                shareCount?.text = count(post.repost)
            }
        }
    }
}