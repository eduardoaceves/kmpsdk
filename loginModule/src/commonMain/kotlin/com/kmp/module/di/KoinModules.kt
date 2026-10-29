/*
 * KoinModules.kt
 * Copyright (c) 2026. All rights reserved
*/
package com.kmp.module.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.kmp.module")
@Configuration
class KoinModules {
    
    @Single
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.Default
    
    /*@Single
    fun httpClient(appDataStore: AppDataStore) = createHttpClient(appDataStore = appDataStore)
    
    @Single
    fun appDatabase() = getDatabaseBuilder()
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
    
    @Single
    fun userDao(database: AppDatabase) = database.userDao()
    
    @Single
    fun packagesDao(database: AppDatabase) = database.packageDAO()
    
    @Single
    fun dataStore() = createDataStore()*/
}