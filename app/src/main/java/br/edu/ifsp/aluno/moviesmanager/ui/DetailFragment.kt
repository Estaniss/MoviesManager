package br.edu.ifsp.aluno.moviesmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Spinner
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentDetailBinding
import br.edu.ifsp.aluno.moviesmanager.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var movie: Movie

    lateinit var nomeET : EditText
    lateinit var yearET : EditText
    lateinit var studioET : EditText
    lateinit var timeET : EditText
    lateinit var isSeeET : CheckBox
    lateinit var gradeET : RatingBar
    lateinit var genreET : Spinner

    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeET = binding.commonLayout.nameET
        yearET = binding.commonLayout.yearET
        studioET = binding.commonLayout.studioET
        timeET = binding.commonLayout.timeET
        isSeeET = binding.commonLayout.isSeeET
        gradeET = binding.commonLayout.gradeET
        genreET = binding.commonLayout.genreSP

        val idMovie= requireArguments().getInt("idMovie")

        viewModel.getMovieById(idMovie)
        viewModel.movie.observe(viewLifecycleOwner){
            result -> result?.let {
                movie -> result
            nomeET.setText(movie.name)
            yearET.setText(movie.year)
            studioET.setText(movie.studio)
            timeET.setText(movie.time)
            isSeeET.isChecked = movie.isSee
            gradeET.rating = movie.grade.toFloat()
            val genresArray = resources.getStringArray(R.array.genreSpinner)
            val genrePosition = genresArray.indexOf(movie.genre)
            genreET.setSelection(genrePosition)

        }
        }

        val menuHost:MenuHost=requireActivity()

        menuHost.addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detail_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.action_editMovie->{
                        movie.name = nomeET.text.toString()
                        movie.year = yearET.text.toString()
                        movie.studio = studioET.text.toString()
                        movie.time = timeET.text.toString()
                        movie.isSee = isSeeET.isChecked
                        movie.grade = gradeET.rating.toInt()
                        val selectedGenre = genreET.selectedItem.toString()
                        movie.genre = selectedGenre



                        viewModel.update(movie)
                        Snackbar.make(binding.root,"Filme Alterado",Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    R.id.action_deleteMovie ->{
                            viewModel.delete(movie)
                        Snackbar.make(binding.root, "Filme Apagado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)
    }








}