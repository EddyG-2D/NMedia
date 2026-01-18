package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.focusAndShoeKeyboard

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post : Post) {
                viewModel.shareById(post.id)
            }

            override fun onRemove(post : Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post : Post) {
                binding.groupEdit.visibility = View.VISIBLE
                viewModel.edit(post)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            val newPost = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) { editedPost ->
            if (editedPost.id == 0L) return@observe
            binding.content.setText(editedPost.content)
            binding.groupText.setText(editedPost.content)
            binding.content.focusAndShoeKeyboard()
        }

        binding.save.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isNullOrBlank()){
              Toast.makeText(
                  this, R.string.error_empty_content, Toast.LENGTH_LONG)
              .show()
              return@setOnClickListener
            }

            binding.content.setOnClickListener{
                binding.groupEdit.visibility = View.VISIBLE
            }

            viewModel.changeContent(text.toString())
            viewModel.saveContent(text)
            binding.content.setText("")
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
            binding.groupEdit.visibility = View.GONE
        }

        binding.clear.setOnClickListener {
            viewModel.cancelEdit()
            binding.content.setText("")
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
            binding.groupEdit.visibility = View.GONE
            }
    }
}

