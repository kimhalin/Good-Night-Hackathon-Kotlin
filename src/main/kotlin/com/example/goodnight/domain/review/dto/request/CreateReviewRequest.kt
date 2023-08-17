package com.example.goodnight.domain.review.dto.request

import com.example.goodnight.domain.movie.domain.Movie
import com.example.goodnight.domain.review.domain.Review

data class CreateReviewRequest (
    val movieId: Long,
    val score: Float,
    val content: String,
) {
    fun toEntity(movie: Movie): Review {
        return Review(
            movie = movie,
            score = this.score,
            content = this.content,
        )
    }
}
