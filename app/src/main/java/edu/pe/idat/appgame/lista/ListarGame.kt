package edu.pe.idat.appgame.lista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import edu.pe.idat.appgame.adaptador.AdaptadorGame
import edu.pe.idat.appgame.databinding.ActivityCatalogBinding
import edu.pe.idat.appgame.model.Game

class ListarGame : AppCompatActivity() {
    var games: MutableList<Game> = mutableListOf()
    lateinit var adaptadorGame: AdaptadorGame
    lateinit var binding:ActivityCatalogBinding
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EventChangeListener()
        binding.rvProductosCatalog.layoutManager=LinearLayoutManager(this)
        binding.rvProductosCatalog.setHasFixedSize(true)
    }

    fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("Games")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                var nombre = document.data["nombre"].toString()
                var descripcion = document.data["descripcion"].toString()
                var edicion = document.data["edicion"].toString()
                var precio = document.data["precio"].toString()
                var reg=Game(nombre, descripcion, edicion,precio)
                games.add(reg)
                }
                adaptadorGame=  AdaptadorGame(games)
                binding.rvProductosCatalog.adapter=adaptadorGame

            }
            .addOnFailureListener {
                //    Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}