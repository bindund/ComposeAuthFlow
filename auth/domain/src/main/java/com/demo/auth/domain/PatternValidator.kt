package com.demo.auth.domain

 interface PatternValidator {
    fun matches(value: String): Boolean
}