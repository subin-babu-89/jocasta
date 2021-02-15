package com.example.jocasta.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jocasta.db.dao.PeopleDao
import com.example.jocasta.db.dao.PeopleRemoteKeysDao
import com.example.jocasta.db.dao.ResourceTypeDao
import com.example.jocasta.db.entity.PeopleRemoteKeys
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.model.People

@Database(
    entities = [ResourceType::class, People::class , PeopleRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class JocastaDatabase : RoomDatabase() {

    abstract fun resourceTypeDao() : ResourceTypeDao

    abstract fun peopleDao() : PeopleDao
    abstract fun peopleRemoteKeysDao() : PeopleRemoteKeysDao

    companion object{
        @Volatile
        private var INSTANCE : JocastaDatabase? = null

        fun getInstance(context: Context) : JocastaDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) : JocastaDatabase{
            return Room.databaseBuilder(context.applicationContext, JocastaDatabase::class.java, "jocasta").build()
        }
    }
}