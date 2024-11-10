package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.contacts.presentation.contactlist.ListUiState
import com.picpay.desafio.android.contacts.presentation.contactlist.UserListAdapter
import com.picpay.desafio.android.contacts.presentation.contactlist.UserListViewModel
import com.picpay.desafio.android.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = UserListAdapter()
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
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
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        }

                        is ListUiState.Success -> {
                            binding.userListProgressBar.visibility = View.GONE
                            listAdapter.submitList(state.data)
                        }
                    }
                }
            }
        }
    }
}
