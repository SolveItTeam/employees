package com.pek.testapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pek.testapp.data.database.dao.EmployeeDao
import com.pek.testapp.data.database.dao.EmployeeWithSpecialityDao
import com.pek.testapp.data.database.dao.SpecialityDao
import com.pek.testapp.data.database.model.Employee
import com.pek.testapp.data.database.model.EmployeeWithSpeciality
import com.pek.testapp.data.database.model.Speciality
import com.pek.testapp.data.database.typeconverter.DatabaseConverters

@Database(
    entities = [Employee::class, Speciality::class, EmployeeWithSpeciality::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class EmployeesDatabase : RoomDatabase() {

    companion object {

        private const val DB_NAME = "EmployeesDatabase"

        @Volatile
        private var instance: EmployeesDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            EmployeesDatabase::class.java,
            DB_NAME
        ).build()

    }

    abstract fun getEmployeeDao(): EmployeeDao

    abstract fun getSpecialityDao(): SpecialityDao

    abstract fun getEmployeesWithSpecialitiesDao(): EmployeeWithSpecialityDao

}