package com.imran.demo.movie.list.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imran.demo.movie.list.R
import com.imran.demo.movie.list.data.model.MovieItem
import com.imran.demo.movie.list.data.model.MovieModel
import com.imran.demo.movie.list.data.network.ApiResponse
import com.imran.demo.movie.list.data.network.LiveDataResource
import com.imran.demo.movie.list.databinding.FragmentMovieListBinding
import com.imran.demo.movie.list.utils.OnItemClickCallback
import com.imran.demo.movie.list.viewmodels.MovieViewModel
import com.imran.demo.movie.list.views.adapters.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(), OnItemClickCallback<MovieItem> {

    private var _binding: FragmentMovieListBinding? = null

    private val movieViewModel by viewModels<MovieViewModel>()

    private var mMovieListAdapter: MovieListAdapter = MovieListAdapter(this)

    private var spinnerAdapter: ArrayAdapter<String>? = null

    private var mMovieListObserver: Observer<LiveDataResource<ApiResponse<MovieModel>>> =
        Observer { data ->
            data?.let { resource ->
                when (resource.status) {
                    LiveDataResource.Status.SUCCESS -> {
                        _binding?.srlMovieList?.isRefreshing = false
                        spinnerAdapter?.clear()
                        spinnerAdapter?.add("Generes")
                        resource.data?.data?.genres?.let { spinnerAdapter?.addAll(it) }
                        spinnerGeneres.setSelection(0)
                        mMovieListAdapter.submitList(resource.data?.data?.movies)
                    }
                    LiveDataResource.Status.ERROR -> {

                    }
                    LiveDataResource.Status.LOADING -> {

                    }
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        movieViewModel.getMovieList()
            .observe(viewLifecycleOwner, mMovieListObserver)
    }

    private fun initView() {
        spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.movies)
            layoutCustomToolbar.btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
            srlMovieList.setOnRefreshListener {
                movieViewModel.getMovieList()
                    .observe(viewLifecycleOwner, mMovieListObserver)
            }
            rvMovieList.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = mMovieListAdapter
            }
            spinnerGeneres.adapter = spinnerAdapter
            spinnerGeneres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    movieViewModel.searchMoviesByGeneres(spinnerGeneres.selectedItem.toString())
                        .observe(viewLifecycleOwner, { data ->
                                mMovieListAdapter.submitList(data)
                            }
                        )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
    }

    override fun onClick(model: MovieItem) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(model)
        )
    }

}