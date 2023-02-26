package kspt.icc.spbstu.ru.dao

import kspt.icc.spbstu.ru.model.User
import kspt.icc.spbstu.ru.model.UserRole

interface DAOFacade {
    suspend fun user(login: String): User?
    suspend fun user(id: Int): User?
    suspend fun addUser(login: String, hashedPassword: String, role: UserRole): User?
    suspend fun deleteUser(id: Int): Boolean
}