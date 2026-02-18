package com.jdpadillavigo.spring_boot_basics_kotlin.repository

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface QuotesRepository: JpaRepository<QuoteEntity, Long> {
    fun findByContentContainsIgnoreCase(query: String): List<QuoteEntity>

//    @Query("""
//        SELECT q
//        FROM QuoteEntity q
//        WHERE q.content = LIKE '%' || :query || '%'
//    """)
    fun searchQuotes(query: String): List<QuoteEntity>
}