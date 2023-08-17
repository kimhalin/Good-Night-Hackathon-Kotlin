package com.example.goodnight.domain.movie.dto.request

import com.example.goodnight.domain.movie.domain.MovieGenre
import java.time.LocalDate

data class UpdateMovieRequest(
    val title: String,
    val genre: MovieGenre,
    val endDate: LocalDate,
    val openDate: LocalDate,
)
