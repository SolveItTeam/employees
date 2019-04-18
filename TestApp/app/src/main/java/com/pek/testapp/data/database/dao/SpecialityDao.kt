package com.pek.testapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pek.testapp.data.database.model.Speciality

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM specialities")
    fun getAllSpecialitiesSync(): List<Speciality>

    @Query("SELECT * FROM specialities")
    fun getAllSpecialitiesLiveData(): LiveData<List<Speciality>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(speciality: Speciality)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(speciality: List<Speciality>)

    @Query("DELETE FROM specialities")
    fun clear()

}