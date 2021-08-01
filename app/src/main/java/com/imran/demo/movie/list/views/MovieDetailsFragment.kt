package com.imran.demo.movie.list.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.imran.demo.movie.list.R
import com.imran.demo.movie.list.databinding.FragmentMovieDetailsBinding
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = args.selectedMovie?.title
            layoutCustomToolbar.btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            Glide.with(requireContext())
                .load(args.selectedMovie?.posterUrl)
                .placeholder(R.drawable.progress_animation)
                .into(imgMovieBanner)

            tvMovieName.text = args.selectedMovie?.title
            tvMovieYear.text = "Release Year: " + args.selectedMovie?.runtime
            tvGenere.text = "Generes: " + args.selectedMovie?.genres.toString()
            tvRuntime.text = "Runtime: " + args.selectedMovie?.runtime + " mins"
            tvActors.text = "Actors: " + args.selectedMovie?.actors
            tvDirectors.text = "Directors: " + args.selectedMovie?.director
            tvPlot.text = "Plot: " + args.selectedMovie?.plot
        }
    }

}