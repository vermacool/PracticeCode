package com.phistream.practicecode.game

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.phistream.practicecode.R
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_create_team.*

class CreateTeamActivity : AppCompatActivity(), OnFragmentInteractionListener {


    lateinit var mPagerAdapter: TeamPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)
        toolbar?.title = "Create Team"
        setSupportActionBar(toolbar)

        mPagerAdapter = TeamPagerAdapter(supportFragmentManager)
        viewPager.adapter = mPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        //Create Tabs and Give name to team
        tabLayout.addTab(tabLayout.newTab().setText("Team A"))
        tabLayout.addTab(tabLayout.newTab().setText("Team B"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null)
                    viewPager.currentItem = tab.position
            }
        })

    }

    override fun onFragmentInteraction(mPlayersList: Any?, teamType: Int) {
        when (teamType) {
            1 -> {
                if (mPlayersList is ArrayList<*>) {
                    val playersList = mPlayersList
                    //first team data as ArrayList<String>() store it
                    Toast.makeText(applicationContext, "" + playersList, Toast.LENGTH_LONG).show()
                }
                /**
                 * First team create created now create 2nd team
                 */
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setMessage("yay..! Team a created. Now Create opponent Team")
                alertDialog.setPositiveButton("Yes") { dialog, position -> run { viewPager.currentItem = teamType + 1 } }
                alertDialog.create()
                alertDialog.show()
            }
            2 -> {
                //2nd team data
                if (mPlayersList is ArrayList<*>) {
                    val playersList = mPlayersList
                    //first team data
                    Toast.makeText(applicationContext, "" + playersList, Toast.LENGTH_LONG).show()
                }
                //Pop fragment from backstack

            }
        }
    }

}
