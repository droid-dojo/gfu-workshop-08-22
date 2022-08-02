package com.example.gfu.workshop.user.data

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.gfu.backend.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random


class UserRepository {


    private val cache: MutableMap<Int, User> = mutableMapOf()


    fun getUser(userId: Int): Flow<User> = flow {
        val cached = cache[userId]

        if (cached != null) {
            emit(cached)
        }

        delay(5_000)

        val backend = User(
            name = LoremIpsum(
                Random.nextInt(from = 1, until = 20)
            ).values.joinToString(),
            location = "Nix"
        )

        cache[userId] = backend

        emit(backend)
    }


    companion object {
        private lateinit var instance: UserRepository

        fun getInstance(): UserRepository {
            if (::instance.isInitialized) {
                return instance
            }
            instance = UserRepository()
            return getInstance()
        }
    }
}