package com.example.goodnight.domain.review.dto.request

data class FindAllReviewsRequest(
    val movieId: Long,
    val score: Float?,
)
