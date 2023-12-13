package br.edu.ifsp.aluno.moviesmanager.repository

import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.data.MovieDAO

class MovieRepository (private val movieDAO:MovieDAO) {
    suspend fun insert(movie:Movie){
        movieDAO.insert(movie)
    }
}