package com.polishone.animalblog.common.data.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.polishone.animalblog.common.domain.model.entity.AniBlogEntity

@Dao
interface BlogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(list: List<AniBlogEntity>)

    @Query("SELECT * FROM aniblog")
    fun getAllBlogItems(): PagingSource<Int, AniBlogEntity>

    @Query("DELETE FROM aniblog")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogKeys(aniList: List<BlogKey>)

    @Query("DELETE FROM aniblog")
    suspend fun deleteAllBlogKey()

    @Query("SELECT * FROM blogkey WHERE id=:id")
    suspend fun getAllKeys(id: String): BlogKey

}