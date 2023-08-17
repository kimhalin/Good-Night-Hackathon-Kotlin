package com.example.goodnight.domain.movie.dto.request

import com.example.goodnight.domain.movie.domain.Movie
import com.example.goodnight.domain.movie.domain.MovieGenre
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class CreateMovieRequest(
    val title: String,

    val genre: MovieGenre,

    @JsonFormat(pattern = "yyyy-MM-dd")
    val endDate: LocalDate,

    @JsonFormat(pattern = "yyyy-MM-dd")
    val openDate: LocalDate,
) {
    fun toEntity(): Movie {
        return Movie(
            title = this.title,
            openDate = this.openDate,
            endDate = this.endDate,
            onScreen = LocalDate.now().isAfter(this.openDate) && LocalDate.now().isBefore(this.endDate),
            genre = this.genre
        )
    }
}
