package com.example.fake_matrial.data.di

import android.content.Context
import com.example.fake_matrial.app.MyApplication
import com.example.fake_matrial.data.gateways.ServerGateway
import com.example.fake_matrial.data.gateways.ServerGatewayImplementer
import com.example.fake_matrial.data.repositoryImp.MaterialRepositoryImp
import com.example.fake_matrial.domain.repository.MaterialRepository
import com.example.fake_matrial.domain.useCases.DownloadMaterialUseCase
import com.example.fake_matrial.domain.useCases.GetAllMaterialsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication = app as MyApplication


    @Singleton
    @Provides
    fun provideServerGateWay(api: Retrofit): ServerGateway =
        ServerGatewayImplementer(api)



    @Singleton
    @Provides
    fun provideMaterialRepository(serverGateway: ServerGateway): MaterialRepository =
        MaterialRepositoryImp(serverGateway)

    @Singleton
    @Provides
    fun provideGetMaterialUseCase(materialRepository: MaterialRepository): GetAllMaterialsUseCase =
        GetAllMaterialsUseCase(materialRepository)
    @Singleton
    @Provides
    fun provideDownloadMaterialUseCase(materialRepository: MaterialRepository): DownloadMaterialUseCase =
        DownloadMaterialUseCase(materialRepository)


}