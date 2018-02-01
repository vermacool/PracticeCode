package com.phistream.practicecode.game

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.phistream.practicecode.R
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_create_team.*

class CreateTeamActivity : AppCompatActivity() {
    lateinit var  realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
