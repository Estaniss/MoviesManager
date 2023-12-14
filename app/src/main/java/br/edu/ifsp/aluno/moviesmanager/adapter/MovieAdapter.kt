package br.edu.ifsp.aluno.moviesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.databinding.MovieCellBinding

class MovieAdapter():
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{
        private lateinit var binding:MovieCellBinding
        var movieList = ArrayList<Movie>()

fun updateList(newList: ArrayList<Movie>){
    movieList = newList
    //movieListFiltable = movieList
    notifyDataSetChanged()
}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
       binding = MovieCellBinding.inflate(LayoutInflater.from(parent.context),parent ,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.nameVH.text = movieList[position].name
        holder.genreVH.text = movieList[position].genre
        holder.gradeVH.rating = movieList[position].grade.toFloat()
        holder.isSeeVH.isChecked = movieList[position].isSee
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(view:MovieCellBinding):RecyclerView.ViewHolder(view.root)
    {
        val nameVH = view.nameTV
        val genreVH = view.genreTV
        val gradeVH = view.ratingBar
        val isSeeVH = view.checkBox
    }

}