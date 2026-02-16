package com.jdpadillavigo.spring_boot_basics_kotlin

class QuoteNotFoundException(
    private val id: Long
): RuntimeException(
    "A quote with ID $id not found"
)