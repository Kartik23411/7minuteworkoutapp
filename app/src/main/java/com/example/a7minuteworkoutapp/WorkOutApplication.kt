package com.example.a7minuteworkoutapp

import android.app.Application
import com.example.a7minuteworkoutapp.database.Graph

class WorkOutApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}