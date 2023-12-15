package br.edu.ifsp.aluno.moviesmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.adapter.MovieAdapter
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentListBinding
import br.edu.ifsp.aluno.moviesmanager.viewmodel.MovieViewModel


class ListFragment:Fragment(){
private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        configureRecyclerView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)

                val searchView = menu.findItem(R.id.action_search).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        movieAdapter.filter.filter(p0)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }




            private fun configureRecyclerView(){
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.allMovies.observe(viewLifecycleOwner){
            list -> list?.let {
                movieAdapter.updateList(list as ArrayList<Movie>)
        }
        }


        val recyclerView = binding.recyclerview
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        val listener = object : MovieAdapter.MovieListener{
            override fun onItemClick(pos: Int) {
                val c = movieAdapter.movieListFilterable[pos]
                val bundle = Bundle()
                bundle.putInt("idMovie",c.id)

                findNavController().navigate(
                    R.id.action_listFragment_to_detailFragment,
                    bundle
                )
            }
        }
    movieAdapter.setClickListener(listener)
















    }

}