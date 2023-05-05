package com.example.androidtask.data.local.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtask.data.local.models.RegisterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

        @Query("SELECT * FROM register_table WHERE email LIKE '%' || :query || '%' ")
        fun getUser(query: String): Flow<RegisterModel>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertUser(userModel: RegisterModel) : Long

}