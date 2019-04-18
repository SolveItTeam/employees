package com.pek.testapp.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.pek.testapp.data.database.EmployeesDatabase
import org.junit.After
import org.junit.Before

abstract class DatabaseTest() {

    private lateinit var _db: EmployeesDatabase
    val db: EmployeesDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EmployeesDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        _db.close()
    }

}