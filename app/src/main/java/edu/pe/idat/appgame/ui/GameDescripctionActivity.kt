package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import edu.pe.idat.appgame.databinding.ActivityGameDescripctionBinding
import java.lang.Exception

class GameDescripctionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGameDescripctionBinding
    private lateinit var database: FirebaseFirestore

    private var nombre: String?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDescripctionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val intent = this.intent
        val extra = intent.extras
        val name = extra?.getString("keyName")
        nombre = name

        Instances()
        GetDataGame( nombre!! )
        Listener()
    }

    private fun Listener(){
        binding.ivRegresarGamedescripction.setOnClickListener {
            val intent = Intent( this, CatalogActivity::class.java )
            startActivity( intent )
            finish()
        }
    }
    private fun Instances(){
        database = FirebaseFirestore.getInstance()
    }

    private fun GetDataGame( name: String ){
        binding.progressBar2.visibility = View.VISIBLE
        if( name != "" ){
            database.collection( "Games" ).whereEqualTo( "name", name )
                .get().addOnSuccessListener {
                    for( data in it ){
                        val image = data.data[ "image" ]
                        val name = data.data[ "name" ]
                        val descripcion = data.data[ "description" ]
                        val edition = data.data[ "edition" ]
                        val precio = data.data[ "precio" ]

                        if( image != null ){
                            Picasso.get().load( image.toString() ).into( binding.ivGameimgGamedescripction, object: com.squareup.picasso.Callback{
                                override fun onSuccess() {
                                    binding.progressBar2.visibility = View.GONE
                                    binding.ivGameimgGamedescripction.visibility = View.VISIBLE
                                    binding.tv404Gamedescription.visibility = View.GONE
                                    binding.textView11.visibility = View.GONE
                                }
                                override fun onError(e: Exception?) {
                                    binding.ivGameimgGamedescripction.visibility = View.VISIBLE
                                    binding.tv404Gamedescription.visibility = View.VISIBLE
                                    binding.textView11.visibility = View.VISIBLE
                                }
                            })
                        }
                        binding.ivNombreGamedescripction.text = name.toString()
                        binding.tvEdicionGamedescripction.text = edition.toString()
                        binding.tvDescripcionGamedescripction.text = descripcion.toString()
                        binding.tvPrecioGamedescription.text = "S/$precio"
                    }
                }
        }
    }
    private fun toast( message: String ){
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show()
    }
}