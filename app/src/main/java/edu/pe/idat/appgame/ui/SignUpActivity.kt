package edu.pe.idat.appgame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import edu.pe.idat.appgame.R
=======
import edu.pe.idat.appgame.databinding.ActivitySignUpBinding
>>>>>>> f1e5d58b3a645fe0228c1b8755b3011d9ebe7e24

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()
    }


}