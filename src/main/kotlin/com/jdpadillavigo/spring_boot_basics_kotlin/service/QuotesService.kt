package com.jdpadillavigo.spring_boot_basics_kotlin.service

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteDto
import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteNotFoundException
import com.jdpadillavigo.spring_boot_basics_kotlin.repository.QuotesRepository
import org.springframework.stereotype.Service

@Service
class QuotesService(
    private val quotesRepository: QuotesRepository
) {
    fun getQuotes() = quotesRepository.getQuotes()

    fun insertQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository.insertQuote(quote)
    }

    fun updateQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository.updateQuote(quote)
    }

    fun deleteQuote(quoteId: Long) {
        if(!quotesRepository.deleteQuote(quoteId)) {
            throw QuoteNotFoundException(quoteId)
        }
    }
}