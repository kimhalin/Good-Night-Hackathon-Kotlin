package com.example.goodnight.domain.movie.domain

import com.example.goodnight.domain.movie.dto.response.MovieWithAvgScoreResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MovieRepository : JpaRepository<Movie, Long> {

    fun findAllByGenreAndOnScreenOrderByOpenDate(genre: MovieGenre, onScreen: Boolean): List<Movie>

    fun findAllByOnScreenOrderByOpenDate(onScreen: Boolean): List<Movie>

    fun findAllByGenreOrderByOpenDate(genre: MovieGenre): List<Movie>

    @Query("SELECT " +
            "m.id AS id, m.title AS title, " +
            "m.openDate AS openDate, m.endDate AS endDate, " +
            "m.genre AS genre, m.onScreen AS onScreen, " +
            "AVG(r.score) AS avgScore " +
            "FROM Movie m "+
            "LEFT JOIN Review r " +
            "ON r.movie.id = m.id " +
            "GROUP BY m.id " +
            "ORDER BY AVG(r.score) DESC")
    fun findAllMoviesOrderByAverageScore(pageable: Pageable): Page<MovieWithAvgScoreResponse>
}