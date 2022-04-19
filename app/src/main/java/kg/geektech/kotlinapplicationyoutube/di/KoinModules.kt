package kg.geektech.kotlinapplicationyoutube.di

import kg.geektech.kotlinapplicationyoutube.core.network.networkModule

val koinModules = listOf(
    repoModules, viewModels, networkModule, connectionModule, prefsModule
)