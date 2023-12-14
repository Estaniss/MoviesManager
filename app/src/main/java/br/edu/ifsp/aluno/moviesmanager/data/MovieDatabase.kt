package br.edu.ifsp.aluno.moviesmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
    abstract class MovieDatabase :RoomDatabase(){
        abstract fun movieDAO():MovieDAO
        companion object{
            @Volatile
            private var INSTANCE : MovieDatabase? = null
            fun getDatabase(context: Context):MovieDatabase{
                return INSTANCE?: synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "moviesmanager"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }