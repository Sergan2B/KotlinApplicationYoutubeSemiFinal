package kg.geektech.kotlinapplicationyoutube.di

import kg.geektech.kotlinapplicationyoutube.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { Repository(get()) }
}