package com.example.goodnight.domain.movie.domain

import com.example.goodnight.global.common.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate

@Entity
@SQLDelete(sql = "UPDATE movie SET deleted_at = current_timestamp WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
class Movie (

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var openDate: LocalDate,

    @Column(nullable = false)
    var endDate: LocalDate,

    @Column(nullable = false)
    var onScreen: Boolean,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var genre: MovieGenre,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseEntity() {

    fun update(title: String, openDate: LocalDate, endDate: LocalDate, genre: MovieGenre) {
        this.title = title
        this.openDate = openDate
        this.endDate = endDate
        this.onScreen = LocalDate.now().isAfter(this.openDate) && LocalDate.now().isBefore(this.endDate)
        this.genre = genre
    }
}