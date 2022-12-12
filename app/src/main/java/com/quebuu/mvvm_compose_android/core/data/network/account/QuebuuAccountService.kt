package com.quebuu.mvvm_compose_android.core.data.network.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class QuebuuAccountService : Service() {
    private lateinit var authenticator: QuebuuAccount

    override fun onCreate() {
        authenticator = QuebuuAccount(this)
    }

    override fun onBind(intent: Intent?): IBinder = authenticator.iBinder
}