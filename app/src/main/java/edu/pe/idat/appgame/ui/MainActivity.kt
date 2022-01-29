package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.pe.idat.appgame.R
import edu.pe.idat.appgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()
        Listener()
    }

    private fun Listener(){
        binding.btnregistrar.setOnClickListener {
            val intent = Intent( this, SignUpActivity::class.java )
            startActivity( intent )
        }
        binding.btnsignin.setOnClickListener {
            val intent = Intent( this, LoginActivity::class.java )
            startActivity( intent )
        }
    }

}