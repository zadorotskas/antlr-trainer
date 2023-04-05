package ru.spbstu.icc.kspt.dao

import ru.spbstu.icc.kspt.model.*
import java.time.LocalDateTime

interface DAOFacade {
    suspend fun user(login: String): User?
    suspend fun user(id: Int): User?
    suspend fun addUser(login: String, hashedPassword: String, role: UserRole): User?
    suspend fun deleteUser(id: Int): Boolean

    suspend fun lesson(id: Int): Lesson?
    suspend fun allLessons(): List<Lesson>
    suspend fun addLesson(name: String, number: Int): Lesson?
    suspend fun deleteLesson(id: Int): Boolean

    suspend fun admin(name: String): Admin?
    suspend fun addAdmin(name: String): Admin?

    suspend fun addTaskSolution(userName: String, lessonId: Int, datetime: LocalDateTime, state: SolutionState, attempt: Long): TaskSolution?

    suspend fun getAttemptsCount(userName: String, lessonId: Int): Long
}