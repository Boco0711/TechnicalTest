package com.leprincesylvain.altentest.technicaltest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leprincesylvain.altentest.technicaltest.data.model.Converters
import com.leprincesylvain.altentest.technicaltest.data.model.Device
import com.leprincesylvain.altentest.technicaltest.data.model.User

@Database(entities = arrayOf(Device::class, User::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: DataDatabase? = null

        fun getDataDatabase(context: Context): DataDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, DataDatabase::class.java, "data_database")
                        .build()
                }
            }
            return INSTANCE!!

        }
    }
}