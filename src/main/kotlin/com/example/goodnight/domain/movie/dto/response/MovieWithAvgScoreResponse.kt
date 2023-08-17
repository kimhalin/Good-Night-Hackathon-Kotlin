package com.example.goodnight.domain.movie.dto.response

import com.example.goodnight.domain.movie.domain.MovieGenre
import java.time.LocalDate

interface MovieWithAvgScoreResponse {
    fun getId(): Long?
    fun getTitle(): String
    fun getOpenDate(): LocalDate
    fun getEndDate(): LocalDate
    fun getOnScreen(): Boolean?
    fun getGenre(): MovieGenre
    fun getAvgScore(): Double?
}
