package com.pek.testapp.db

import androidx.test.runner.AndroidJUnit4
import com.pek.testapp.data.database.model.Employee
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import org.hamcrest.MatcherAssert.assertThat

@RunWith(value = AndroidJUnit4::class)
class EmployeeDaoTest : DatabaseTest() {

    @Test
    fun insertOneAndRead() {
        val employee = createEmployee(
            id = 1L,
            firstName = "fName",
            lastName = "lName",
            birthday = Date(123456),
            photo = "link"
        )

        db.getEmployeeDao().insert(employee)
        val employees = db.getEmployeeDao().getAllSync()
        assertThat(employees.size, CoreMatchers.`is`(1))
        assertThat(employees.first().firstName, CoreMatchers.`is`("fName"))
        assertThat(employees.first().lastName, CoreMatchers.`is`("lName"))
        assertThat(employees.first().birthday, CoreMatchers.`is`(Date(123456)))
        assertThat(employees.first().photo, CoreMatchers.`is`("link"))
    }

    @Test
    fun insertAllAndDeleteAll() {
        db.getEmployeeDao().insert(
            createEmployees(
                count = 10,
                id = 0,
                firstName = "fName",
                lastName = "lName",
                birthday = Date(123456),
                photo = "link"
            )
        )

        db.getEmployeeDao().getAllSync().apply {
            assertThat(size, CoreMatchers.`is`(10))
            forEach { assertThat(it.firstName, CoreMatchers.startsWith("fName")) }
        }

        db.getEmployeeDao().clear()

        db.getEmployeeDao().getAllSync().apply {
            assertThat(isEmpty(), CoreMatchers.`is`(true))
        }
    }

}