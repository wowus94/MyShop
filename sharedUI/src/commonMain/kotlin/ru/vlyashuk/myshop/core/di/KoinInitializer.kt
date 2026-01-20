package ru.vlyashuk.myshop.core.di

import org.koin.core.context.startKoin

object KoinInitializer {
    private var started = false

    fun init() {
        if (!started) {
            startKoin {

            }
            started = true
        }
    }
}
