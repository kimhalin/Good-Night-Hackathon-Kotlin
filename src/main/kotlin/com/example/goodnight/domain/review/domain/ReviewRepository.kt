package com.example.goodnight.domain.review.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReviewRepository : JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
            "WHERE r.movie.id = :movieId and r.score >= :score " +
            "ORDER BY r.createdAt DESC")
    fun findAllByMovieIdAndScore(movieId: Long, score: Float): List<Review>
}