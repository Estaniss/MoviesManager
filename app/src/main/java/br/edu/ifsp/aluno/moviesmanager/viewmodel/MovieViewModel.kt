package br.edu.ifsp.aluno.moviesmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.data.MovieDatabase
import br.edu.ifsp.aluno.moviesmanager.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application):AndroidViewModel(application) {
    private val repository:MovieRepository
    lateinit var movie:LiveData<Movie>
    var allMovies:LiveData<List<Movie>>

    init {
        val dao = MovieDatabase.getDatabase(application).movieDAO()
        repository= MovieRepository(dao)
        allMovies = repository.getAllMovies()
    }
    fun insert(movie: Movie)= viewModelScope.launch(Dispatchers.IO){
    repository.insert(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.update(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(movie)
    }
    fun getMovieById(id:Int){
        viewModelScope.launch {
            movie=repository.getMovieById(id)
        }
    }
}