package br.com.wcsm.gymtrack.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkKoinModule : KoinModule {
    override val module = module {
        single {
            provideRetrofit()
        }

        /*single<MainActivity.JsonPlaceholder> {
            get<Retrofit>().create(MainActivity.JsonPlaceholder::class.java)
        }

        // For test
        singleOf(MainActivity::JsonPlaceholderRepositoryImpl) bind MainActivity.JsonPlaceholderRepository::class

        viewModelOf(MainActivity::JsonPlaceViewModel)*/
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/") // For test
            .build()
    }
}