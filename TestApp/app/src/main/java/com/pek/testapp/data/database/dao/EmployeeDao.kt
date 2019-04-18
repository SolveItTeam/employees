package com.pek.testapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pek.testapp.data.database.model.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllSync(): List<Employee>

    @Query("SELECT * FROM employees WHERE id = :employeeId")
    fun getEmployeeByIdSync(employeeId: Long): Employee

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Employee): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: List<Employee>): List<Long>

    @Query("DELETE FROM employees")
    fun clear()

}