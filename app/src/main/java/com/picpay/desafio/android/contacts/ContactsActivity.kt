package com.picpay.desafio.android.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.contacts.presentation.contactlist.ContactListFragment
import com.picpay.desafio.android.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding

    private val backPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment())
                .addToBackStack(null)
                .commit()
        }

        onBackPressedDispatcher.addCallback(this, backPressed)
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressed.remove()
    }

    companion object {
        fun newIntent(context: Context) {
            val intent = Intent(context, ContactsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
