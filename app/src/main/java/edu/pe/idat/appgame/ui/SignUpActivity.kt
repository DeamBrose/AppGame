package edu.pe.idat.appgame.ui
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import edu.pe.idat.appgame.databinding.ActivitySignUpBinding
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import edu.pe.idat.appgame.model.Cliente
import java.util.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var storage: StorageReference

    private var photoUri: Uri ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()

        Instances()
        Listener()

    }

    private fun Instances(){
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance().reference.child("PhotoClient")
    }
    private fun Listener(){
        binding.tvopenGallery.setOnClickListener { OpenGallery() }
        binding.btnRegistrarSignup.setOnClickListener {
            if( ValidateData() ){
                binding.progressBar5.visibility = View.VISIBLE
                binding.btnRegistrarSignup.visibility = View.GONE
                RegisterPhoto()
            }
        }
    }

    private fun RegisterPhoto(){
        val filename= UUID.randomUUID().toString()
        val ref = storage.child( filename )
        ref.putFile( photoUri!! ).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                RegisterUser( it.toString() )
            }
        }
    }
    private fun RegisterUser( url: String ){
        val email = binding.tilCorreoSignup.text.toString()
        val password = binding.tilContraseniaSignup.text.toString()

        val cliente = Cliente(
            email,
            password,
            url
        )
        RegisterCorreo( email, password )
        database.collection( "Clients" )
            .add( cliente ).addOnSuccessListener {
                binding.progressBar5.visibility = View.GONE
                binding.btnRegistrarSignup.visibility = View.VISIBLE
                toast( "Usuario Registrado." )
            }
            .addOnFailureListener {
                binding.progressBar5.visibility = View.GONE
                binding.btnRegistrarSignup.visibility = View.VISIBLE
                toast( "Error con el registro." )
            }
    }
    private fun RegisterCorreo( email: String, password: String ){
        auth.createUserWithEmailAndPassword( email, password ).addOnCompleteListener {
            if( it.isSuccessful ){
                //toast( "Registro Exitoso en el Correo." )
            }else{
                //toast( "Ocurrio un error en el Correo." )
            }
        }
    }

    private fun OpenGallery(){
        val response = Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI )
        startActivityForResult( response, 1001 )
    }

    private fun ValidateData(): Boolean {
        if( photoUri == null ){
            toast( "Ingrese su foto." )
            return false
        }else if( binding.tilCorreoSignup.text.toString().isEmpty() ){
            toast( "Ingrese su email." )
            return false
        }else if( binding.tilContraseniaSignup.text.toString().isEmpty() ){
            toast( "Seleccione una contraseña." )
            return false
        }else {
            return true
        }
    }

    private fun toast( message: String ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1001) {
            photoUri = data?.data
            binding.ivFotoperfilSignup.setImageURI( photoUri )
        }
    }
}