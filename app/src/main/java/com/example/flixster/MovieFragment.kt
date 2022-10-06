package com.example.flixster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

import com.example.flixster.Movie
import com.example.flixster.MovieRecyclerViewAdapter
import com.example.flixster.OnListFragmentInteractionListener
import com.example.flixster.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class MovieFragment : Fragment(), OnListFragmentInteractionListener {
    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        //val popRecyclerView = view.findViewById<View>(R.id.)
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateNowAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateNowAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        // Using the client, perform the HTTP request
        client[
                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                params,
                object : JsonHttpResponseHandler()
                {
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: okhttp3.Headers?,
                json: JSON?
            ) {
                // The wait for a response is over
                progressBar.hide()
                val resultsJson = json!!.jsonObject.get("results").toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                val models : List<Movie> = gson.fromJson(resultsJson, arrayMovieType)
                recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)
            }

            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: okhttp3.Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                // The wait for a response is over
                progressBar.hide()

                // If the error is not null, log it!
                throwable?.message?.let {
                    Log.e("MovieFragment", response.toString())
                }
            }
                }]
    }

    /*
     * What happens when a particular movie is clicked.
     */
    override fun onItemClick(item: Movie, position: Int) {
        //Toast.makeText(context, "test: " + item.title + "position" + position, Toast.LENGTH_LONG).show()
        val intent = Intent(context, DetailActivity::class.java)
        //println(item.javaClass.name)
        intent.putExtra("movieTitle", item.title)
        intent.putExtra("movieOverview", item.overview)
        intent.putExtra("movieImage", item.poster_path)
        intent.putExtra("vote_average", item.vote_average)
        intent.putExtra("releaseDate", item.releaseDate)
        context?.startActivity(intent)
    }

}
