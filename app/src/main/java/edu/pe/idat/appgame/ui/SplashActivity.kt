package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.pe.idat.appgame.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()
        Splash()
    }

    private fun Splash(){
        CoroutineScope( Dispatchers.Main ).launch {
            delay( 3000L )
            val intent = Intent( this@SplashActivity, LoginActivity::class.java )
            startActivity( intent )
            finish()
        }
    }
}