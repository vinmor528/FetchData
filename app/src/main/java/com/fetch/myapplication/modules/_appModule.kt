package com.fetch.myapplication.modules

import com.fetch.myapplication.Constants
import com.fetch.myapplication.MainViewModel
import com.fetch.myapplication.repository.FetchAPI
import com.fetch.myapplication.repository.FetchRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideAPI(get()) }
}

val repositoryModule = module {
    single { FetchRepository(get()) }
    viewModel { MainViewModel(get()) }
}

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .readTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

private fun provideAPI(retrofit: Retrofit): FetchAPI = retrofit.create(FetchAPI::class.java)