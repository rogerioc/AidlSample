package com.rogerio.aidlservicesample

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.widget.Toast

class RemoteService : Service() {
    private val mHandler = object: Handler() {
        override fun handleMessage(msg: Message?) {
            Toast.makeText(applicationContext, (msg?.obj as? String), Toast.LENGTH_LONG).show()
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
       return binder
    }

    private val binder = object : IServiceAIDLInterface.Stub() {
        override fun sendMessage(message: String?) {
            val msg = Message.obtain()
            msg.obj = message
            msg.target = mHandler
            msg.sendToTarget()
        }

    }
}