package com.example.goodnight.domain.movie.dto.response

import com.example.goodnight.domain.movie.domain.Movie
import com.example.goodnight.domain.movie.domain.MovieGenre
import java.time.LocalDate

data class MovieResponse(
    val id: Long,

    val title: String,

    val openDate: LocalDate,

    val endDate: LocalDate,

    val onScreen: Boolean,

    val genre: MovieGenre,
) {
    companion object {
        fun of(movie: Movie): MovieResponse {
            return MovieResponse(
                id = movie.id!!,
                title = movie.title,
                openDate = movie.openDate,
                endDate = movie.endDate,
                onScreen = movie.onScreen,
                genre = movie.genre
            )
        }
    }
}
