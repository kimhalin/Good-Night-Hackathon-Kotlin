package com.example.goodnight.domain.movie.application

import com.example.goodnight.domain.movie.domain.Movie
import com.example.goodnight.domain.movie.domain.MovieRepository
import com.example.goodnight.domain.movie.dto.request.CreateMovieRequest
import com.example.goodnight.domain.movie.dto.request.FindAllMoviesRequest
import com.example.goodnight.domain.movie.dto.request.UpdateMovieRequest
import com.example.goodnight.domain.movie.dto.response.MovieResponse
import com.example.goodnight.domain.movie.dto.response.MovieWithAvgScoreResponse
import com.example.goodnight.global.common.dto.PaginatedRequest
import com.example.goodnight.global.common.dto.PaginatedResponse
import com.example.goodnight.global.util.findByIdOrThrow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService constructor(
    private val movieRepository: MovieRepository,
){

    @Transactional
    fun saveMovie(request: CreateMovieRequest) {
        val movie = request.toEntity()
        movieRepository.save(movie)
    }

    @Transactional
    fun deleteMovie(id: Long) {
        movieRepository.deleteById(id)
    }

    @Transactional
    fun updateMovie(id: Long, request: UpdateMovieRequest) {
        val movie = movieRepository.findByIdOrThrow(id)
        movie.update(request.title, request.openDate, request.endDate, request.genre)
    }

    fun findOneMovie(id: Long): MovieResponse {
        val movie = getOneMovie(id)
        return MovieResponse.of(movie)
    }

    fun findAllMovies(request: FindAllMoviesRequest): List<MovieResponse> {
        val movieList: List<Movie>
        if (request.onScreen == null && request.genre == null) {
            return movieRepository.findAll().map { movie -> MovieResponse.of(movie) }
        }
        if (request.onScreen == null) {
            movieList = movieRepository.findAllByGenreOrderByOpenDate(request.genre!!)
        } else if (request.genre == null) {
            movieList = movieRepository.findAllByOnScreenOrderByOpenDate(request.onScreen)
        }
        else {
            movieList = movieRepository.findAllByGenreAndOnScreenOrderByOpenDate(request.genre, request.onScreen)
        }
        return movieList.map { movie -> MovieResponse.of(movie) }
    }

    fun findAllMoviesOrderByAverageScore(request: PaginatedRequest): PaginatedResponse<MovieWithAvgScoreResponse> {
        val pageable = PageRequest.of(request.page, request.size)
        val results = movieRepository.findAllMoviesOrderByAverageScore(pageable)
        return PaginatedResponse.of(results.content, results.totalPages, results.totalElements,
            results.pageable.pageSize, results.pageable.pageNumber, results.pageable.offset)
    }

    fun getOneMovie(id: Long): Movie {
        return movieRepository.findByIdOrThrow(id)
    }
}