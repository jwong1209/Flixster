package com.example.flixster

import java.io.Serializable

/**
 * This interface is used by the [MovieRecyclerViewAdapter] to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by [MovieFragment]
 */
interface OnListFragmentInteractionListener {
    fun onItemClick(item: Movie, position: Int)
}
