package ru.spbstu.icc.kspt


object AuthName {
    const val SESSION = "session"
    const val OAUTH = "oauth-google"
    const val SESSION_ADMIN = "admin"
}

object CommonRoutes {
    const val LOGIN = "/login"
    const val LOGOUT = "/logout"
    const val LESSON = "/lesson"
    const val ALL_LESSONS = "/lesson/all"
    const val AUTH = "/auth"
}

object Cookies {
    const val AUTH_COOKIE = "auth"
}
