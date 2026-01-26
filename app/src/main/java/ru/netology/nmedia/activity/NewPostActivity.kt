package ru.netology.nmedia.activity


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ok.setOnClickListener {
            val text = binding.edit.text.toString()
            val video = binding.video.text.toString().takeIf { it.isNotBlank() }
            if (text.isBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                val intent = Intent().apply {
                    putExtra(Intent.EXTRA_TEXT, text)
                    putExtra("video", video)
                }
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}

data class PostContent(val content: String, val video: String?)

object NewPostContract : ActivityResultContract<Unit, PostContent?>() {
    override fun createIntent(context: Context, input: Unit) = Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): PostContent? {
        return if (resultCode == RESULT_OK) {
            val content = intent?.getStringExtra(Intent.EXTRA_TEXT) ?: return null
            val video = intent.getStringExtra("video")
            PostContent(content, video)
        } else {
            null
        }
    }
}
