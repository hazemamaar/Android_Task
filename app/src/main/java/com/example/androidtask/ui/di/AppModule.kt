package com.example.androidtask.ui.di

import android.content.Context
import androidx.room.Room
import com.example.androidtask.BuildConfig
import com.example.androidtask.data.repository.UserRepoImpl
import com.example.androidtask.data.local.roomdb.UserDB
import com.example.androidtask.data.local.roomdb.UserDao
import com.example.androidtask.data.remote.ApiService
import com.example.androidtask.domain.repository.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val localHttpLoggingInterceptor = HttpLoggingInterceptor()
        localHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return localHttpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder()
                return@addInterceptor chain.proceed(builder.build())
            }
            .build()

    @Provides
    fun providesApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .run {
                baseUrl(BuildConfig.BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context,
    ) = context


//    @Singleton
//    @Provides
//    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
//        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
//
//    @Singleton
//    @Provides
//    fun provideSharedPreferencesEditor(preferences: SharedPreferences): SharedPreferences.Editor =
//        preferences.edit()
//
//    @Singleton
//    @Provides
//    fun provideGson(): Gson = Gson()
//
//    @Singleton
//    @Provides
//    fun provideComplexPreference(
//        GSON: Gson,
//        preferences: SharedPreferences,
//        editor: SharedPreferences.Editor,
//    ): ComplexPreferences =
//        ComplexPreferences(GSON, preferences, editor)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDB {
        return Room.databaseBuilder(
            appContext,
            UserDB::class.java,
            "user_db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun provideSearchHistoryDao(roomDataBase: UserDB)= roomDataBase.userDao

    @Provides
    fun provideRepository(userDao: UserDao,apiService: ApiService) :UserRepo{
        return UserRepoImpl(userDao,apiService)
    }

}