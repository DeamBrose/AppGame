package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.pe.idat.appgame.preferences.SharedPreferences
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

            if( SharedPreferences.constantes.getCliente() == "ADMIN" ){
                val intent = Intent( this@SplashActivity, NewGameActivity::class.java )
                startActivity( intent )
                finish()
            }else if( SharedPreferences.constantes.getCliente() == "CLIENT" ){
                val intent = Intent( this@SplashActivity, CatalogActivity::class.java )
                startActivity( intent )
                finish()
            }else{
                if( SharedPreferences.constantes.getCliente().isEmpty() ) {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
    }
}