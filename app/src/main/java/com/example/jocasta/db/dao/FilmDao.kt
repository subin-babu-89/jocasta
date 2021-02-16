package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Film

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Film>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: Film)

    @Query("SELECT * FROM films WHERE title LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Film>

    @Query("SELECT * FROM films WHERE url LIKE :filmUrl")
    suspend fun filmByURL(filmUrl: String): List<Film>

    @Query("DELETE FROM films")
    suspend fun clearAll()
}