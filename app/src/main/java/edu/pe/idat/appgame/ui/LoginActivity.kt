package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import edu.pe.idat.appgame.R
import edu.pe.idat.appgame.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()
        Listener()
    }

    private fun Listener(){
        binding.btnIniciarsesionLogin.setOnClickListener {
            SignIn()
        }
    }
    private fun SignIn(){
        val email = binding.tilCorreoLogin.text.toString()
        val password = binding.tilContraseniaLogin.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword( email, password )
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    val intent = Intent( this, MainActivity::class.java )
                    intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
                    startActivity( intent )
                    toast( "Welcome to AppGame." )
                }else{
                    toast( "Email or Password Incorrect." )
                }

            }
    }

    private fun toast( message: String ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }
}