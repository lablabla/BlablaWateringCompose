package com.lablabla.blablawatering.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lablabla.blablawatering.data.local.WateringDatabase
import com.lablabla.blablawatering.data.remote.MockRemoteApiImpl
import com.lablabla.blablawatering.data.remote.RemoteApiBTImpl
import com.lablabla.blablawatering.data.repository.BluetoothServiceImpl
import com.lablabla.blablawatering.data.repository.WateringRepositoryImpl
import com.lablabla.blablawatering.domain.repository.BluetoothService
import com.lablabla.blablawatering.domain.repository.RemoteApi
import com.lablabla.blablawatering.domain.repository.WateringRepository
import com.welie.blessed.BluetoothCentralManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBluetoothCentralManager(@ApplicationContext context: Context): BluetoothCentralManager {
        return BluetoothCentralManager(context)
    }

    @Provides
    @Singleton
    fun providesRemoteApi(bluetoothService: BluetoothService): RemoteApi {
        return RemoteApiBTImpl(bluetoothService)
    }

    @Provides
    @Singleton
    fun providesBluetoothService(central: BluetoothCentralManager): BluetoothService {
        return BluetoothServiceImpl(central)
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