package com.example.goodnight.domain.movie.api

import com.example.goodnight.domain.movie.application.MovieService
import com.example.goodnight.domain.movie.dto.request.CreateMovieRequest
import com.example.goodnight.domain.movie.dto.request.FindAllMoviesRequest
import com.example.goodnight.domain.movie.dto.request.UpdateMovieRequest
import com.example.goodnight.domain.movie.dto.response.MovieResponse
import com.example.goodnight.domain.movie.dto.response.MovieWithAvgScoreResponse
import com.example.goodnight.global.common.dto.PaginatedRequest
import com.example.goodnight.global.common.dto.PaginatedResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController constructor(
    private val movieService: MovieService,
) {
    @PostMapping
    fun saveMovie(@RequestBody request: CreateMovieRequest) {
        movieService.saveMovie(request)
    }

    @PatchMapping("/{id}")
    fun updateMovie(@RequestBody request: UpdateMovieRequest, @PathVariable id: Long) {
        movieService.updateMovie(id, request)
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Long) {
        movieService.deleteMovie(id)
    }

    @GetMapping("/{id}")
    fun findOneMovie(@PathVariable id: Long): MovieResponse {
        return movieService.findOneMovie(id)
    }

    @GetMapping("/avg")
    fun findAllMoviesWithAvgScore(request: PaginatedRequest): PaginatedResponse<MovieWithAvgScoreResponse> {
        return movieService.findAllMoviesOrderByAverageScore(request)
    }

    @GetMapping
    fun findAllMovies(request: FindAllMoviesRequest): List<MovieResponse> {
        return movieService.findAllMovies(request)
    }
}