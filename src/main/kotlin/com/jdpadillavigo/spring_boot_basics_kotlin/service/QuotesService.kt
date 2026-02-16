package com.jdpadillavigo.spring_boot_basics_kotlin.service

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteDto
import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteNotFoundException
import com.jdpadillavigo.spring_boot_basics_kotlin.config.QuotesConfig
import com.jdpadillavigo.spring_boot_basics_kotlin.repository.QuotesRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
//@Profile("dev")
class QuotesService(
    private val quotesRepository: QuotesRepository,
    @param:Value($$"${spring.application.version}")
    private val version: String,
    @param:Value($$"${MY_CUSTOM_ENV_VARIABLE}")
    private val customVariable: String,
    private val quotesConfig: QuotesConfig
) {
    init {
        println("Spring Boot backend running with version $version")
        println("Custom variable is $customVariable")

        println(quotesConfig)
    }

    fun getQuotes(query: String?): List<QuoteDto> {
        val quotes = if(!query.isNullOrBlank() && query.length >= quotesConfig.search.minLength) {
            quotesRepository.getQuotes().filter {
                it.content.contains(query, quotesConfig.search.ignoreCase)
            }
        } else {
            quotesRepository.getQuotes()
        }

        return quotes
    }

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