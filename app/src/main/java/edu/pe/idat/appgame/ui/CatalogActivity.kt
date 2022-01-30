package edu.pe.idat.appgame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import edu.pe.idat.appgame.ui.adaptador.AdaptadorGame
import edu.pe.idat.appgame.databinding.ActivityCatalogBinding
import edu.pe.idat.appgame.model.Game
import edu.pe.idat.appgame.preferences.SharedPreferences
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class CatalogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatalogBinding
    private lateinit var adaptadorGame: AdaptadorGame
    private lateinit var db: FirebaseFirestore

    var games: MutableList<Game> = mutableListOf()
    val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate( layoutInflater )
        setContentView( binding.root )
        supportActionBar?.hide()

        EventChangeListener()
        binding.rvProductosCatalog.layoutManager= LinearLayoutManager(this)
        binding.rvProductosCatalog.setHasFixedSize(true)
        list( binding.sldOfertasCatalog )
        Listener()
    }

    private fun Listener(){
        binding.ibCerrarsesiionCatalog.setOnClickListener {
            SharedPreferences.constantes.saveCliente( "" )
            val intent = Intent( this, MainActivity::class.java )
            startActivity( intent )
            finish()
        }
    }
    private fun list( carrusel : ImageCarousel){
        list.add( CarouselItem("https://staticctf.akamaized.net/J3yJr34U2pZ2Ieem48Dwy9uqj5PNUQTn/5o0p2Ug7hSTQhU9UHUeKaU/f0656242a93710228342414d5cdf6fad/BrandNewsArticle_lunar-sale-ACV-2022_960x540_MEX-ES.jpg") )
        list.add( CarouselItem("https://staticctf.akamaized.net/J3yJr34U2pZ2Ieem48Dwy9uqj5PNUQTn/3I5LEAVEWDmWXjnl6ct6Cs/043ff5350e52da18f007219abb785af5/BrandNewsArticle_lunar-sale-FC6-2022_960x540_MEX-ES.jpg") )
        list.add( CarouselItem("https://store.ubi.com/on/demandware.static/-/Sites-masterCatalog/la/dw73581861/images/content/AC/ACH_UCS4439_ProductImage_1920x1080_US.jpg") )
        carrusel.addData( list )
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Games")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var name = document.data["name"].toString()
                    var description = document.data["description"].toString()
                    var edition = document.data["edition"].toString()
                    var precio = document.data["precio"].toString()
                    var image = document.data["image"].toString()
                    var reg = Game(name, description, edition, precio, image)
                    games.add(reg)
                }
                adaptadorGame = AdaptadorGame(games)
                binding.rvProductosCatalog.adapter = adaptadorGame
                adaptadorGame.notifyDataSetChanged()

            }
            .addOnFailureListener {
                //    Log.d(TAG, "Error getting documents: ", exception)
            }
    }

}