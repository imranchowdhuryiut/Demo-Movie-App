package com.imran.demo.movie.list.views.adapters

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imran.demo.movie.list.R
import com.imran.demo.movie.list.data.model.MovieItem
import com.imran.demo.movie.list.utils.OnItemClickCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
class MovieListAdapter(
    private var mCallback: OnItemClickCallback<MovieItem>? = null
) : ListAdapter<MovieItem, MovieViewHolder>(MovieItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

}

class MovieViewHolder(
    override val containerView: View,
    private val mCallback: OnItemClickCallback<MovieItem>? = null
) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    private var mModel: MovieItem? = null

    init {
        containerView.containerViewMovie.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }

    fun bindView(item: MovieItem?) {
        mModel = item
        Glide.with(containerView.context)
            .load(item?.posterUrl)
            .placeholder(R.drawable.progress_animation)
            .into(containerView.imgMovie)

        containerView.tvMovieName.text = item?.title
        containerView.tvMovieYear.text = "Release Year: " + item?.runtime
        containerView.tvGenere.text = "Generes: " + item?.genres.toString()
        containerView.tvRuntime.text = "Runtime: " + item?.runtime + " mins"
        containerView.tvActors.text = "Actors: " + item?.actors
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            listener: OnItemClickCallback<MovieItem>? = null
        ): MovieViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            return MovieViewHolder(itemView, listener)
        }
    }

}

@Suppress("ReplaceCallWithBinaryOperator")
object MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.equals(newItem)

}