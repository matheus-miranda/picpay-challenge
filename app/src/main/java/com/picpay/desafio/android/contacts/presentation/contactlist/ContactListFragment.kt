package com.picpay.desafio.android.contacts.presentation.contactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.contacts.presentation.contactlist.adapter.UserListAdapter
import com.picpay.desafio.android.core.network.ConnectivityManager
import com.picpay.desafio.android.databinding.FragmentContactListBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: UserListAdapter
    private val connectivityManager: ConnectivityManager by inject()
    private val viewModel: ContactListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeState()
        addRecyclerViewScrollListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        listAdapter = UserListAdapter()
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListUiState.collect { state ->
                    when (state) {
                        ListUiState.Loading -> {
                            binding.userListProgressBar.visibility = View.VISIBLE
                        }

                        is ListUiState.Error -> {
                            val message = getString(R.string.error)
                            binding.userListProgressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }

                        is ListUiState.Success -> {
                            val message = getString(R.string.error_internet)
                            binding.userListProgressBar.visibility = View.GONE
                            listAdapter.submitList(state.data)
                            if (connectivityManager.isOnline().not()) {
                                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addRecyclerViewScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0) {
                    onRecyclerViewScrolledDown()
                } else if (dy > 0) {
                    onRecyclerViewScrolledUp()
                }
            }
        })
    }

    private fun onRecyclerViewScrolledDown() {
        val toolbarHeight = resources.getDimensionPixelSize(R.dimen.action_bar_default_height)
        binding.recyclerView.setPadding(0, toolbarHeight, 0, 0)
    }

    private fun onRecyclerViewScrolledUp() {
        binding.recyclerView.setPadding(0, 0, 0, 0)
    }
}
