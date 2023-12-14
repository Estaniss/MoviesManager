package br.edu.ifsp.aluno.moviesmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val year:String,
    val studio:String,
    val time:String,
    val isSee: Boolean,
    val grade:Int,
    val genre:String
)

