package com.example.flixster

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var releaseDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        movieImageView = findViewById(R.id.movieImage)
        titleTextView = findViewById(R.id.movieTitle)
        ratingTextView = findViewById(R.id.movieRating)
        overviewTextView = findViewById(R.id.movieOverview)
        releaseDateTextView = findViewById(R.id.releaseDate)

        // TODO: Get the extra from the Intent
        val title = intent.getSerializableExtra("movieTitle") as String
        val vote_average = intent.getSerializableExtra("vote_average") as Double
        val poster_path = intent.getSerializableExtra("movieImage") as String
        val overview = intent.getSerializableExtra("movieOverview") as String
        val releaseDate = intent.getSerializableExtra("releaseDate") as String

        // TODO: Set the title, byline, and abstract information from the article
        //titleTextView.text = article.headline?.main
        titleTextView.text = title
        ratingTextView.text = "Rating: " + vote_average.toString() + " / 10"
        //bylineTextView.text = article.byline?.original
        overviewTextView.text = overview
        releaseDateTextView.text = "Release Date: " + releaseDate.substring(5) + "-" + releaseDate.substring(0,4)

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + poster_path)
            .into(movieImageView)
    }
}