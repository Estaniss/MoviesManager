package br.edu.ifsp.aluno.moviesmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var name:String,
    var year:String,
    var studio:String,
    var time:String,
    var isSee: Boolean,
    var grade:Int,
    var genre:String,
)

