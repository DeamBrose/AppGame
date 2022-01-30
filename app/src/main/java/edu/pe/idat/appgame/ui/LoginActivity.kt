package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import edu.pe.idat.appgame.preferences.SharedPreferences
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
            binding.progressBar4.visibility = View.VISIBLE
            binding.btnIniciarsesionLogin.visibility = View.GONE
            SignIn()
        }
        binding.ivRegresarLogin.setOnClickListener{
            val intent = Intent( this, MainActivity::class.java )
            startActivity( intent )
        }
    }
    private fun SignIn(){
        val email = binding.tilCorreoLogin.text.toString()
        val password = binding.tilContraseniaLogin.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword( email, password )
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    if( email == "emilio@gmail.com" ){
                        val intent = Intent( this, NewGameActivity::class.java )
                        SharedPreferences.constantes.saveCliente( "ADMIN" )
                        SharedPreferences.constantes.savekey( email )
                        startActivity( intent )
                        finish()
                        toast( "Welcome Admin to AppGame." )
                    }else{
                        SharedPreferences.constantes.saveCliente( "CLIENT" )
                        val intent = Intent( this, CatalogActivity::class.java )
                        startActivity( intent )
                        finish()
                        toast( "Welcome to AppGame." )
                    }

                }else{
                    binding.progressBar4.visibility = View.GONE
                    binding.btnIniciarsesionLogin.visibility = View.VISIBLE
                    toast( "Email or Password Incorrect." )
                }

            }
    }

    private fun toast( message: String ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }
}