package edu.pe.idat.appgame.preferences

import android.content.Context

class Constantes( val context: Context ) {
    val SHARED_DBNAME = "Mydtb"

    //Contantes
    val SHARED_CLIENT = "SHARED_CLIENT"
    val KEY = "KEY"

    val storage = context.getSharedPreferences( SHARED_DBNAME, 0 )

    fun saveCliente( email: String ){
        storage.edit().putString( SHARED_CLIENT, email ).apply()
    }
    fun getCliente(): String{
        return storage.getString( SHARED_CLIENT, "" )!!
    }

    fun savekey( email: String ){
        storage.edit().putString( KEY, email ).apply()
    }
    fun getkey(): String{
        return storage.getString( KEY, "" )!!
    }
}