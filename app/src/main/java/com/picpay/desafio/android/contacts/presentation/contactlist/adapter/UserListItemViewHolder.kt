package com.picpay.desafio.android.contacts.presentation.contactlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.contacts.domain.model.User
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding: ListItemUserBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.run {
            name.text = user.name
            username.text = user.username
            progressBar.visibility = View.VISIBLE
        }

        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(binding.picture, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.GONE
                }
            })
    }

    companion object {
        fun create(parent: ViewGroup): UserListItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ListItemUserBinding.inflate(inflater, parent, false)
            return UserListItemViewHolder(itemBinding)
        }
    }
}
