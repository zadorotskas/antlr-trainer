package ru.spbstu.icc.kspt.dao

import ru.spbstu.icc.kspt.model.Theory
import ru.spbstu.icc.kspt.model.User
import ru.spbstu.icc.kspt.model.UserRole

interface DAOFacade {
    suspend fun user(login: String): User?
    suspend fun user(id: Int): User?
    suspend fun addUser(login: String, hashedPassword: String, role: UserRole): User?
    suspend fun deleteUser(id: Int): Boolean

    suspend fun theory(id: Int): Theory?
    suspend fun addTheory(name: String, number: Int, path: String): Theory?
    suspend fun deleteTheory(id: Int): Boolean
}