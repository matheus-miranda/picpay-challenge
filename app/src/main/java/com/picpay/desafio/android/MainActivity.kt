package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.presentation.contactlist.ListUiState
import com.picpay.desafio.android.contacts.presentation.contactlist.UserListAdapter
import com.picpay.desafio.android.contacts.presentation.contactlist.UserListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListUiState.collect { state ->
                    when (state) {
                        ListUiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is ListUiState.Error -> {
                            val message = getString(R.string.error)
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.GONE
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        }

                        is ListUiState.Success -> {
                            progressBar.visibility = View.GONE
                            adapter.users = state.data
                        }
                    }
                }
            }
        }
    }
}
