package edu.pe.idat.appgame.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import edu.pe.idat.appgame.databinding.ActivityNewGameBinding
import edu.pe.idat.appgame.model.Cliente
import edu.pe.idat.appgame.model.Game
import java.util.*

class NewGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewGameBinding
    private lateinit var database: FirebaseFirestore
    private lateinit var storage: StorageReference

    private var imgUri: Uri?=null
    private var email: String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val intent = this.intent
        val extra = intent.extras
        val correo = extra?.getString("keyemail")
        email = correo

        Instances()
        GetDataUser( email!! )
        Listener()

    }

    private fun Instances(){
        database = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance().reference.child("PhotoGame")
    }
    private fun Listener(){
        binding.btnAgregarNewgame.setOnClickListener {
            if( ValidateData() ){ RegisterPhoto() }
        }
        binding.ivImgjuegoNewgame.setOnClickListener{
            OpenGallery()
            binding.tvSubirfotoNewgame.visibility = View.INVISIBLE
        }
        binding.ivCerrarsesionNewgame.setOnClickListener {
            val intent = Intent( this, MainActivity::class.java )
            startActivity( intent )
            finish()
        }
    }

    private fun GetDataUser( email: String ){
        if( email != "" ){
            database.collection( "Clients" ).whereEqualTo( "username", email )
                .get().addOnSuccessListener {
                    for( data in it ){
                        val photo = data.data[ "photo" ]
                        if( photo != null ){
                            Picasso.get().load( photo.toString() ).into( binding.ivPerfilimageNewgame )
                            toast( "Foto Cargada..." )
                        }
                    }
                }
        }
    }
    private fun CleanData(){
        imgUri = null
        binding.ivImgjuegoNewgame.setImageURI( imgUri )
        binding.tvSubirfotoNewgame.visibility = View.VISIBLE
        binding.tvEdicionNewgame.setText( "" )
        binding.etPrecioNewgame.setText( "" )
        binding.etNombreNewgame.setText( "" )
        binding.etDescripcionNewgame.setText( "" )
    }
    private fun RegisterPhoto(){
        toast( "Subiendo..." )
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAgregarNewgame.visibility = View.INVISIBLE

        val filename= UUID.randomUUID().toString()
        val ref = storage.child( filename )
        ref.putFile( imgUri!! ).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                RegisterGame( it.toString() )
            }
        }
    }
    private fun RegisterGame( url: String ){
        val edicion = binding.tvEdicionNewgame.text.toString()
        val precio = binding.etPrecioNewgame.text.toString()
        val nombre = binding.etNombreNewgame.text.toString()
        val descrip = binding.etDescripcionNewgame.text.toString()

        val game = Game( nombre, descrip, edicion, precio, url )
        database.collection( "Games" )
            .add( game ).addOnSuccessListener {
                toast( "Juego Registrado" )
                CleanData()
                binding.progressBar.visibility = View.INVISIBLE
                binding.btnAgregarNewgame.visibility = View.VISIBLE
            }
            .addOnFailureListener { toast( "Error con el registro." ) }
    }

    private fun OpenGallery(){
        val response = Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI )
        startActivityForResult( response, 1001 )
    }
    private fun ValidateData(): Boolean {
        if( imgUri == null ){
            toast( "Ingrese una Foto." )
            return false
        }else if( binding.tvEdicionNewgame.text.toString().isEmpty() ){
            toast( "Ingrese nombre de la edición." )
            return false
        }else if( binding.etPrecioNewgame.text.toString().isEmpty() ){
            toast( "Ingrese el precio." )
            return false
        }else if( binding.etNombreNewgame.text.toString().isEmpty() ){
            toast( "Ingrese el nombre." )
            return false
        }else if( binding.etDescripcionNewgame.text.toString().isEmpty() ){
            toast( "Ingrese la descripción." )
            return false
        }else{
            return true
        }
    }

    private fun toast( message: String ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode == RESULT_OK && requestCode == 1001 ) {
            imgUri = data?.data
            binding.ivImgjuegoNewgame.setImageURI( imgUri )
        }
    }
}