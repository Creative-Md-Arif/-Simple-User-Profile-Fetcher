package com.example.simpleuserprofilefetcher

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.simpleuserprofilefetcher.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetchUserProfile()

        // ProgressBar Initialization
        progressBar = findViewById(R.id.progressBar)

        // TextViews Initialization
        val nameText: TextView = findViewById(R.id.nameText)
        val usernameText: TextView = findViewById(R.id.usernameText)
        val emailText: TextView = findViewById(R.id.emailText)
        val phoneText: TextView = findViewById(R.id.phoneText)
        val addressText: TextView = findViewById(R.id.addressText)
        val errorText: TextView = findViewById(R.id.errorText)

        // Observe user data
        viewModel.user.observe(this, { user ->
            nameText.text = user.name
            usernameText.text = user.username
            emailText.text = user.email
            phoneText.text = user.phone
            addressText.text = "${user.address.street}, ${user.address.city}"
        })

        // Observe error data
        viewModel.error.observe(this, { error ->
            errorText.text = error
        })

        // Observe loading state and control ProgressBar visibility
        viewModel.loading.observe(this, { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }
}

