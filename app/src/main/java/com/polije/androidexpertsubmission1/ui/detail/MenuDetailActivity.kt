package com.polije.androidexpertsubmission1.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.polije.androidexpertsubmission1.R
import com.polije.androidexpertsubmission1.core.domain.model.Agent
import com.polije.androidexpertsubmission1.databinding.ActivityMenuDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDetailBinding
    private val viewModel: MenuDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailAgent = getParcelableExtra(intent, EXTRA_DATA, Agent::class.java)
        showDetails(detailAgent)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private fun showDetails(detailAgent: Agent?) {
        detailAgent?.let {
            supportActionBar?.title = detailAgent.displayName
            binding.contentDetailMenu.tvDeveloperName.text = getString(R.string.developer_name, detailAgent.developerName)
            binding.contentDetailMenu.tvDescription.text = getString(R.string.description, detailAgent.description)
            Glide.with(this@MenuDetailActivity)
                .load(detailAgent.fullPortrait)
                .into(binding.ivDetailImage)

            var statusFavorite = detailAgent.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMenu(detailAgent, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }
}