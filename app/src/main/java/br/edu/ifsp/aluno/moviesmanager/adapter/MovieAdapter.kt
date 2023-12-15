package br.edu.ifsp.aluno.moviesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.aluno.moviesmanager.data.Movie
import br.edu.ifsp.aluno.moviesmanager.databinding.MovieCellBinding
import android.widget.Filter
import android.widget.Filterable

class MovieAdapter():
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(),Filterable
{
        private lateinit var binding:MovieCellBinding

        var movieList = ArrayList<Movie>()
        var listener: MovieListener? = null
        var movieListFilterable = ArrayList<Movie>()

fun updateList(newList: ArrayList<Movie>){
    movieList = newList
    movieListFilterable = movieList
    notifyDataSetChanged()
}

    fun setClickListener(listener: MovieListener)
    {
        this.listener = listener
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
        holder.yearVH.text = movieList[position].year
        holder.gradeVH.rating = movieList[position].grade.toFloat()
        holder.isSeeVH.isChecked = movieList[position].isSee
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(view:MovieCellBinding):RecyclerView.ViewHolder(view.root)
    {
        val nameVH = view.nameTV
        val yearVH = view.yearET
        val gradeVH = view.ratingBar
        val isSeeVH = view.checkBox
        init {
            view.root.setOnClickListener{
                listener?.onItemClick(adapterPosition)
            }
        }
    }
    interface MovieListener
    {
        fun onItemClick(pos: Int)
    }

    override fun getFilter():Filter{
        return object :Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty())
                    movieListFilterable = movieList
                else
                {
                    val resultList = ArrayList<Movie>()
                    for (row in movieList)
                        if(row.name.lowercase().contains(p0.toString().lowercase()))
                            resultList.add(row)
                    movieListFilterable = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = movieListFilterable
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                movieListFilterable = p1?.values as ArrayList<Movie>
                notifyDataSetChanged()
            }
        }
    }














}