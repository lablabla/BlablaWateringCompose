package com.lablabla.blablawatering.di

import android.app.Application
import androidx.room.Room
import com.lablabla.blablawatering.data.local.WateringDatabase
import com.lablabla.blablawatering.data.remote.MockRemoteApiImpl
import com.lablabla.blablawatering.data.remote.RemoteApiBTImpl
import com.lablabla.blablawatering.data.repository.WateringRepositoryImpl
import com.lablabla.blablawatering.domain.repository.RemoteApi
import com.lablabla.blablawatering.domain.repository.WateringRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRemoteApi(): RemoteApi {
        return MockRemoteApiImpl()
    }

    @Provides
    @Singleton
    fun providesWateringDatabase(app: Application): WateringDatabase {
        return Room.databaseBuilder(
            app,
            WateringDatabase::class.java,
            "watering_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesWateringRepository(
        api: RemoteApi,
        db: WateringDatabase
    ): WateringRepository {
        return WateringRepositoryImpl(
            api,
            db
        )
    }
}