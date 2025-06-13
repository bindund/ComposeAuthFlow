package com.demo.auth.data.di

import com.demo.auth.data.AuthRepositoryImpl
import com.demo.auth.data.EmailPatternValidator
import com.demo.auth.domain.AuthRepository
import com.demo.auth.domain.PatternValidator
import com.demo.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module



val authDataModule = module{
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}