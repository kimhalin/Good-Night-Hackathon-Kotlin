package com.example.goodnight.global.common.dto

data class PaginatedResponse<T>(
    val items: List<T>,
    val totalPages: Int,
    val totalElements: Long,
    val pageSize: Int,
    val pageNumber: Int,
    val offset: Long,
) {
    companion object {
        fun <T> of(
            items: List<T>,
            totalPages: Int,
            totalElements: Long,
            pageSize: Int,
            pageNumber: Int,
            offset: Long
        ): PaginatedResponse<T> {
            return PaginatedResponse(items, totalPages, totalElements, pageSize, pageNumber, offset)
        }
    }
}