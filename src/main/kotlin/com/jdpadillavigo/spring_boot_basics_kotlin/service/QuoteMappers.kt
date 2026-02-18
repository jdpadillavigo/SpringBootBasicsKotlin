package com.jdpadillavigo.spring_boot_basics_kotlin.service

import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteDto
import com.jdpadillavigo.spring_boot_basics_kotlin.QuoteEntity

fun QuoteEntity.toDto(): QuoteDto {
    return QuoteDto(
        id = this.id,
        content = this.content,
        author = this.author
    )
}

fun QuoteDto.toEntity(): QuoteEntity {
    return QuoteEntity(
        id = this.id,
        content = this.content,
        author = this.author
    )
}