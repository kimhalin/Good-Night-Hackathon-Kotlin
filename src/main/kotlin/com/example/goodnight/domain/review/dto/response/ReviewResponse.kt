package com.example.goodnight.domain.review.dto.response

import com.example.goodnight.domain.review.domain.Review

data class ReviewResponse (
    val id: Long,
    val score: Float,
    val content: String,
) {
    companion object {
        fun of (review: Review): ReviewResponse {
            return ReviewResponse(
                id = review.id!!,
                score = review.score,
                content = review.content,
            )
        }
    }
}
