package com.polije.androidexpertsubmission1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.androidexpertsubmission1.R
import com.polije.androidexpertsubmission1.core.data.Resource
import com.polije.androidexpertsubmission1.core.ui.AgentAdapter
import com.polije.androidexpertsubmission1.databinding.FragmentHomeBinding
import com.polije.androidexpertsubmission1.ui.detail.MenuDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val agentAdapter = AgentAdapter()
            agentAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, MenuDetailActivity::class.java)
                intent.putExtra(MenuDetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            viewModel.menu.observe(viewLifecycleOwner) { menu ->
                if (menu != null) {
                    when (menu) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            agentAdapter.submitList(menu.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                menu.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvMenu) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = agentAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}