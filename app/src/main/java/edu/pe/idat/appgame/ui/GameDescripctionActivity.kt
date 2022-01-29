package edu.pe.idat.appgame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.pe.idat.appgame.databinding.ActivityGameDescripctionBinding

class GameDescripctionActivity : AppCompatActivity() {
    private lateinit var binding:ActivityGameDescripctionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDescripctionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}