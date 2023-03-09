package ru.spbstu.icc.kspt.dao

import ru.spbstu.icc.kspt.model.Lesson
import ru.spbstu.icc.kspt.model.User
import ru.spbstu.icc.kspt.model.UserRole

interface DAOFacade {
    suspend fun user(login: String): User?
    suspend fun user(id: Int): User?
    suspend fun addUser(login: String, hashedPassword: String, role: UserRole): User?
    suspend fun deleteUser(id: Int): Boolean

    suspend fun lesson(id: Int): Lesson?
    suspend fun allLessons(): List<Lesson>
    suspend fun addLesson(name: String, number: Int): Lesson?
    suspend fun deleteLesson(id: Int): Boolean
}