package com.example.z2.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.z2.databinding.FragmentMoviesBinding
import com.example.z2.ui.adapters.MovieAdapter
import com.example.z2.ui.models.Movie
import com.example.z2.ui.models.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val moviesViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)*/

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        context?.let {
            adapter = MovieAdapter.create(it)
            binding.rvMovies.layoutManager = LinearLayoutManager(it)
            binding.rvMovies.adapter = adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyapi.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieAPI: MovieAPI = retrofit.create(MovieAPI::class.java)

        movieAPI.getMovie().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(p0: Call<List<Movie>>, response: Response<List<Movie>>) {
                response.body()?.let { movie ->
                    adapter.refreshMovies(movie)
                }
            }

            override fun onFailure(p0: Call<List<Movie>>, p1: Throwable) {
                Log.e("Error", "Failed to fetch data")
            }
        })

        val dashboardViewModel =
            ViewModelProvider(this)[MoviesViewModel::class.java]
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}