package br.edu.ifsp.aluno.moviesmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.moviesmanager.R
import br.edu.ifsp.aluno.moviesmanager.adapter.MovieAdapter
import br.edu.ifsp.aluno.moviesmanager.databinding.FragmentListBinding
import br.edu.ifsp.aluno.moviesmanager.viewmodel.MovieViewModel


class ListFragment:Fragment(){
private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var movieAdapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        configureRecyclerView()
        return binding.root
    }

    private fun configureRecyclerView(){
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }

}