package edu.pe.idat.appgame.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.pe.idat.appgame.R
import edu.pe.idat.appgame.databinding.ActivityCatalogBinding
import edu.pe.idat.appgame.databinding.ItemCardGameBinding
import edu.pe.idat.appgame.model.Game


class AdaptadorGame(private val dataset: MutableList<Game>) :
RecyclerView.Adapter<AdaptadorGame.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var binding:ItemCardGameBinding= ItemCardGameBinding.bind(view)
        var contexto: Context= view.context
        fun enlazarGame(g:Game){
            var vinculo=g.image.toString()
            binding.ivGameimgItemgame.setImageBitmap(null)
            if (vinculo.toString().length > 0){
                Picasso.get().load(vinculo).into(binding.ivGameimgItemgame)
            }
            binding.tvNombreItemgame.text.toString()
            binding.tvNombreItemgame.text.toString()
            binding.tvPrecioItemgame.text.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.activity_catalog, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val elemento: Game = dataset.get(position)

    }

    override fun getItemCount()=dataset.size


}


