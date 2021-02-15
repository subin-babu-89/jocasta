package com.example.jocasta.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jocasta.db.dao.*
import com.example.jocasta.db.entity.*
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.People
import com.example.jocasta.network.model.Planet
import com.example.jocasta.network.model.Species

@Database(
    entities = [ResourceType::class,
        People::class , PeopleRemoteKeys::class,
        Planet::class, PlanetRemoteKeys::class,
        Film::class, FilmRemoteKeys::class,
        Species::class, SpeciesRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class JocastaDatabase : RoomDatabase() {

    abstract fun resourceTypeDao() : ResourceTypeDao

    abstract fun peopleDao() : PeopleDao
    abstract fun peopleRemoteKeysDao() : PeopleRemoteKeysDao

    abstract fun planetDao() : PlanetDao
    abstract fun planetRemoteKeysDao() : PlanetRemoteKeysDao

    abstract fun filmDao() : FilmDao
    abstract fun filmRemoteKeysDao() : FilmRemoteKeysDao

    abstract fun speciesDao() : SpeciesDao
    abstract fun speciesRemoteKeysDao() : SpeciesRemoteKeysDao

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