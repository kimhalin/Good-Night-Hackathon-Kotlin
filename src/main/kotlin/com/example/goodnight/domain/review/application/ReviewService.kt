package com.example.goodnight.domain.review.application

import com.example.goodnight.domain.movie.application.MovieService
import com.example.goodnight.domain.review.domain.ReviewRepository
import com.example.goodnight.domain.review.dto.request.CreateReviewRequest
import com.example.goodnight.domain.review.dto.request.FindAllReviewsRequest
import com.example.goodnight.domain.review.dto.response.ReviewResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewService constructor(
    private val reviewRepository: ReviewRepository,
    private val movieService: MovieService,
){

    @Transactional
    fun saveReview(request: CreateReviewRequest) {
        val movie = movieService.getOneMovie(request.movieId)
        val review = request.toEntity(movie)

        reviewRepository.save(review)
    }

    fun findAllReviews(request: FindAllReviewsRequest): List<ReviewResponse> {
        val score = request.score ?: 0f
        return reviewRepository.findAllByMovieIdAndScore(request.movieId, score)
            .map { review -> ReviewResponse.of(review) }
    }
}
