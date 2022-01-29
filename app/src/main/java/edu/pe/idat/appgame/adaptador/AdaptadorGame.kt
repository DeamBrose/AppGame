package edu.pe.idat.appgame.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.pe.idat.appgame.R
import edu.pe.idat.appgame.databinding.ItemCardGameBinding
import edu.pe.idat.appgame.model.Game
import edu.pe.idat.appgame.ui.GameDescripctionActivity

class AdaptadorGame(private val dataset: MutableList<Game>) :
RecyclerView.Adapter<AdaptadorGame.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var binding = ItemCardGameBinding.bind(view)
        //var contexto: Context= view.context

        fun enlazarGame( g:Game ){
            val vinculo = g.image

            if ( vinculo.isNotEmpty() ){
                Picasso.get().load(vinculo).into(binding.ivGameimgItemgame)
            }
            binding.tvNombreItemgame.text = g.name
            binding.tvEdicionItemgame.text = g.edition
            binding.tvPrecioItemgame.text = "S/${ g.precio }"
            binding.root.setOnClickListener{
                val intent = Intent( binding.root.context, GameDescripctionActivity::class.java )
                intent.putExtra( "keyName", g.name )
                binding.root.context.startActivity( intent )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.item_card_game, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elemento: Game = dataset.get(position)
        holder.enlazarGame( elemento )
    }

    override fun getItemCount()=dataset.size


}


