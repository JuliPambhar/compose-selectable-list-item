package com.app.electroluxassigment.di

import com.app.electroluxassigment.data.DataSourceImpl
import com.app.electroluxassigment.data.IDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDataSource(): IDataSource {
        return DataSourceImpl()
    }

}