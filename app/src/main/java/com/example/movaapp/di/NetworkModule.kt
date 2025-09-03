    package com.example.movaapp.di

    import android.content.Context
    import androidx.room.Room
    import com.example.movaapp.api.AuthInterceptor
    import com.example.movaapp.api.MovieApiService
    import com.example.movaapp.local.MovieDatabase
    import com.example.movaapp.local.WatchListDao
    import com.google.firebase.auth.FirebaseAuth
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.android.qualifiers.ApplicationContext
    import dagger.hilt.components.SingletonComponent
    import okhttp3.OkHttpClient
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import javax.inject.Singleton


    @Module
    @InstallIn(SingletonComponent::class)
    class NetworkModule {

        @Singleton
        @Provides
        fun provideFiresbaseAuth(): FirebaseAuth{
            return FirebaseAuth.getInstance()
        }


        @Singleton
        @Provides
        fun provideAuthInterceptor(): AuthInterceptor {
            return AuthInterceptor()
        }



        @Singleton
        @Provides
        fun provideOkhttp(authInterceptor: AuthInterceptor): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
        }



        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
            return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        }




        @Singleton
        @Provides
        fun provideService(retrofit: Retrofit): MovieApiService {
            return retrofit.create(MovieApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideRoom(@ApplicationContext context: Context):MovieDatabase{
            return Room.databaseBuilder(context,MovieDatabase::class.java,"watchlist").build()
        }
        @Provides
        @Singleton
        fun tododao(movieDatabase :MovieDatabase):WatchListDao{
            return movieDatabase.createTodoListDao()
        }
    }
