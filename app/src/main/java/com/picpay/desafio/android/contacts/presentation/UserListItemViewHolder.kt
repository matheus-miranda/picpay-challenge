package com.picpay.desafio.android.contacts.presentation

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.contacts.data.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.findViewById<TextView>(R.id.name).text = user.name
        itemView.findViewById<TextView>(R.id.username).text = user.username
        itemView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.findViewById(R.id.picture), object : Callback {
                override fun onSuccess() {
                    itemView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
            })
    }
}