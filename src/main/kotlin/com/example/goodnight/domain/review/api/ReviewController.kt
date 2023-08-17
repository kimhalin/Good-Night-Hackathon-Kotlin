package com.example.goodnight.domain.review.api

import com.example.goodnight.domain.review.application.ReviewService
import com.example.goodnight.domain.review.dto.request.CreateReviewRequest
import com.example.goodnight.domain.review.dto.request.FindAllReviewsRequest
import com.example.goodnight.domain.review.dto.response.ReviewResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/reviews")
class ReviewController constructor(
    private val reviewService: ReviewService,
){

    @PostMapping
    fun saveReview(@RequestBody request: CreateReviewRequest) {
        reviewService.saveReview(request)
    }


    @GetMapping
    fun findAllReviews(request: FindAllReviewsRequest): List<ReviewResponse> {
        return reviewService.findAllReviews(request)
    }

}