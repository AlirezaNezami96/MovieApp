package alireza.nezami.network.di

import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.dataSource.RetrofitNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun RetrofitNetwork.binds(): NetworkDataSource
}
