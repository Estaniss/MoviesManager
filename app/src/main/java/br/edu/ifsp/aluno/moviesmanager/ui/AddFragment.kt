package br.edu.ifsp.aluno.moviesmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentAddBinding
import br.edu.ifsp.aluno.moviesmanager.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar

class AddFragment:Fragment(){
    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding

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
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost:MenuHost =requireActivity()

        menuHost.addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.action_addMovie -> {
                        val name = binding?.commonLayout?.nameET?.text.toString()
                        val year = binding?.commonLayout?.yearET?.text.toString()
                        val studio = binding?.commonLayout?.studioET?.text.toString()
                        val time = binding?.commonLayout?.timeET?.text.toString()
                        val isSee = binding?.commonLayout?.isSeeET?.isChecked!!
                        val grade = binding?.commonLayout?.gradeET?.rating?.toInt()!!
                        val genre = binding?.commonLayout?.genreSP?.selectedItem.toString()

                        val movie = Movie(0,name, year,studio,time,isSee,grade,genre)
                        viewModel.insert(movie)
                        binding?.let { Snackbar.make(it.root,"Filme adicionado",Snackbar.LENGTH_SHORT).show() }
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner,Lifecycle.State.RESUMED)

    }

}