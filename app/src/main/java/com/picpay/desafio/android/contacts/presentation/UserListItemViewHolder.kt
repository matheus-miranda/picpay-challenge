package com.picpay.desafio.android.contacts.presentation

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.contacts.data.UserResponse
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(userResponse: UserResponse) {
        itemView.findViewById<TextView>(R.id.name).text = userResponse.name
        itemView.findViewById<TextView>(R.id.username).text = userResponse.username
        itemView.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        Picasso.get()
            .load(userResponse.img)
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
