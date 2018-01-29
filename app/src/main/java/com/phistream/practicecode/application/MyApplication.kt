package com.phistream.practicecode.application

import android.app.Application
import io.realm.Realm

/**
 * Created by Biswajit on 1/29/2018.
 * All rights reserved by BiswajitApps
 */
class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}