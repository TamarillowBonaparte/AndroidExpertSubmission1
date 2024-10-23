package com.polije.androidexpertsubmission1.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.androidexpertsubmission1.core.ui.AgentAdapter
import com.polije.androidexpertsubmission1.favorite.databinding.ActivityFavoriteBinding
import com.polije.androidexpertsubmission1.ui.detail.MenuDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Agent Favorite"

        val agentAdapter = AgentAdapter()
        agentAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, MenuDetailActivity::class.java)
            intent.putExtra(MenuDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.favoriteMenu.observe(this@FavoriteActivity) { dataMenu ->
            agentAdapter.submitList(dataMenu)
            binding.viewEmpty.root.visibility =
                if (dataMenu.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvMenu) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = agentAdapter
        }
    }
}