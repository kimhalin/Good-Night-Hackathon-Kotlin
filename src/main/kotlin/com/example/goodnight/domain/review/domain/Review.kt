package com.example.goodnight.domain.review.domain

import com.example.goodnight.domain.movie.domain.Movie
import com.example.goodnight.global.common.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE review SET deleted_at = current_timestamp WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
class Review (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    var movie: Movie,

    var score: Float,

    var content: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseEntity() {
}