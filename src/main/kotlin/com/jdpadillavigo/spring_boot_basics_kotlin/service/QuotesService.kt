package com.jdpadillavigo.spring_boot_basics_kotlin.service

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteDto
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
        return if(query != null) {
            quotesRepository
//                .searchQuotes(query)
                .findByContentContainsIgnoreCase(query)
                .map { it.toDto() }
        } else {
            quotesRepository
                .findAll()
                .map { it.toDto() }
        }
    }

    fun insertQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository
            .save(
                quote.toEntity().apply {
                    this.id = 0
                }
            )
            .toDto()
    }

    fun updateQuote(quote: QuoteDto): QuoteDto {
        return quotesRepository
            .save(
                quote.toEntity()
            )
            .toDto()
    }

    fun deleteQuote(quoteId: Long) {
        quotesRepository.deleteById(quoteId)
    }
}