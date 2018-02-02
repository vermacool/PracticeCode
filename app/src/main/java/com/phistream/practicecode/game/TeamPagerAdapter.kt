package com.phistream.practicecode.game

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Biswajit on 2/1/2018.
 * All rights reserved by BiswajitApps
 */
class TeamPagerAdapter(private var fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val fragment:Fragment =IndividualTeamFragment()
        val args =Bundle()
        args.putString(IndividualTeamFragment.ARG_PARAM1,"${position+1}")
        fragment.arguments = args
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}