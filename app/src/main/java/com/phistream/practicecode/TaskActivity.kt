package com.phistream.practicecode

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.phistream.practicecode.architecture.activity.ArchitectureActivity
import com.phistream.practicecode.game.CreateTeamActivity
import com.phistream.practicecode.path.MapsActivity
import com.phistream.practicecode.webrtc.WebRtc

import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.content_task.*

class TaskActivity : AppCompatActivity() {
    private var permissions = arrayOf(Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(toolbar)
        if (!hasPermissions(this, *permissions)) {
            val permissionAll = 1
            ActivityCompat.requestPermissions(this, permissions, permissionAll)
        }
        taskListView.layoutManager = LinearLayoutManager(this)
        taskListView.itemAnimator = DefaultItemAnimator()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val taskList = ArrayList<Model>()
        taskList.add(Model("Draw Polyline", "On touch drop marker and make "))
        taskList.add(Model("Make alarm", "On on reaching home play alarm"))
        taskList.add(Model("Game", "Create Team to play"))
        taskList.add(Model("ViewGroups", "different View Types"))
        taskList.add(Model("WebRtc", "Video calling"))
        taskList.add(Model("Architecture", "Architecture Component"))
        taskListView.adapter = Adapter(taskList) {
            when (it.title) {
                "Draw Polyline" -> {
                    startActivity(Intent(this, MapsActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
                "Make alarm" -> {
                    Toast.makeText(applicationContext, "alarm", Toast.LENGTH_SHORT).show()
                }
                "Game" -> {
                    startActivity(Intent(this, CreateTeamActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                }
                "ViewGroups" -> {
                    startActivity(Intent(this, CreateTeamActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                }
                "WebRtc" -> {
                    startActivity(Intent(this, WebRtc::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                }
                "Architecture" -> {
                    startActivity(Intent(this, ArchitectureActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                }
            }
        }

        // Example of a call to a native method
        /*sample_text.text = stringFromJNI()*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            permissions
                    .filter { ActivityCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }
                    .forEach { return false }
        }
        return true
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
