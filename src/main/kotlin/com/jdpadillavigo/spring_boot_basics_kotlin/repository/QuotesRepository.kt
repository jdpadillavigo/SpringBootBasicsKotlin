package com.jdpadillavigo.spring_boot_basics_kotlin.repository

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteDto
import org.springframework.stereotype.Repository

@Repository
class QuotesRepository {
    val quotes = mutableListOf<QuoteDto>()

    fun getQuotes() = quotes

    fun insertQuote(quote: QuoteDto): QuoteDto {
        quotes.add(quote)
        return quote
    }

    fun updateQuote(quote: QuoteDto): QuoteDto {
        val index = quotes.indexOfFirst { it.id == quote.id }
        quotes[index] = quote
        return quote
    }

    fun deleteQuote(quoteId: Long): Boolean {
        val quoteToDelete = quotes.find { it.id == quoteId }
        return if (quoteToDelete != null) {
            quotes.remove(quoteToDelete)
            true
        } else {
            false
        }
    }
}