package edu.pe.idat.appgame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.pe.idat.appgame.R
import edu.pe.idat.appgame.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()
    }


}