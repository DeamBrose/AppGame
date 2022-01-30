package edu.pe.idat.appgame.preferences

import android.app.Application

class SharedPreferences: Application() {
    companion object{
        lateinit var constantes: Constantes
    }

    override fun onCreate() {
        super.onCreate()
        constantes = Constantes( applicationContext )
    }
}