package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = List(10) {index ->
        val id = index + 1L
        Post(
            id = id,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "$index Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            liked = 150,
            likedByMe = false,
            repost = 30,
            repostByMe = false
        )
    }
        .reversed()

    private val data = MutableLiveData(posts)

    override fun get(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(likedByMe = !post.likedByMe,
                    liked = post.liked + if (post.likedByMe) -1 else 1)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            post.copy(repostByMe = true, repost = post.repost +1)
    }
    data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            listOf(
                post.copy(
                    id = nextId++,
                    published = "Now",
                    author = "Me"
                )
            ) + posts
        } else {
            posts.map { it: Post ->
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        }
        data.value = posts
    }
}