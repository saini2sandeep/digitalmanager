package com.sandeep.digitalmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TenantDetails::class], version = 1, exportSchema = false)
abstract class DigitalManagerDataBase : RoomDatabase() {
    abstract val digitalManagerDataBaseDao: DigitalManagerDataBaseDao


    companion object {

        @Volatile
        private var INSTANCE: DigitalManagerDataBase? = null

        fun getInstance(context: Context): DigitalManagerDataBase {

            /**
             * Multiple threads can ask for INSTANCE of database but wrapping it in [synchronized]
             * only one thread of execution at a time can enter this block of code.
             * Which make sure Database only initialised once.
             */
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DigitalManagerDataBase::class.java,
                        "digital_manager"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}