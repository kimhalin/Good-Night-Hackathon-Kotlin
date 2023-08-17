package com.example.goodnight.domain.movie.dto.request

import com.example.goodnight.domain.movie.domain.MovieGenre

data class FindAllMoviesRequest(
    val genre: MovieGenre?,
    val onScreen: Boolean?,
)
