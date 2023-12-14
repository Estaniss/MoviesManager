package br.edu.ifsp.aluno.moviesmanager.repository

import androidx.lifecycle.LiveData
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.data.MovieDAO

class MovieRepository (private val movieDAO:MovieDAO) {
    suspend fun insert(movie:Movie){
        movieDAO.insert(movie)
    }
    fun getAllMovies():LiveData<List<Movie>>{
        return movieDAO.getAllMovies()
    }
}