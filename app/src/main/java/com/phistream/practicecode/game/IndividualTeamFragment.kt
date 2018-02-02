package com.phistream.practicecode.game

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phistream.practicecode.R
import kotlinx.android.synthetic.main.fragment_individual_team.view.*

/**
 * Activities that contain this fragment must implement the
 * [IndividualTeamFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class IndividualTeamFragment : Fragment() {

    var mParam1: String? = null
    lateinit var inflatedView: View
    lateinit var playerList: ArrayList<String>
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
        }
        playerList = ArrayList()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        inflatedView = inflater!!.inflate(R.layout.fragment_individual_team, container, false)
        return inflatedView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inflatedView.tvPlayerDetails.text = "Enter player's details of team ${mParam1}"
        inflatedView.btnSubmit.setOnClickListener { view -> run { fetchFilledData() } }

    }

    /**
     * Validate data if its not null & blank add it to
     * @param playerList i.e ArrayList<String>
     */
    private fun fetchFilledData() {
        if (!inflatedView.etFirstPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etFirstPlayer.text.toString())
        }

        if (!inflatedView.etSecondPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etSecondPlayer.text.toString())
        }
        if (!inflatedView.etThirdPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etThirdPlayer.text.toString())
        }
        if (!inflatedView.etFourthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etFourthPlayer.text.toString())
        }
        if (!inflatedView.etFifthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etFifthPlayer.text.toString())
        }
        if (!inflatedView.etSixthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etSixthPlayer.text.toString())
        }
        if (!inflatedView.etSeventhPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etSeventhPlayer.text.toString())
        }
        if (!inflatedView.etEighthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etEighthPlayer.text.toString())
        }
        if (!inflatedView.etNinethPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etNinethPlayer.text.toString())
        }
        if (!inflatedView.etTenthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etTenthPlayer.text.toString())
        }
        if (!inflatedView.etEleventhPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etEleventhPlayer.text.toString())
        }
        if (!inflatedView.etTwelfthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etTwelfthPlayer.text.toString())
        }
        if (!inflatedView.etThirteenthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etThirteenthPlayer.text.toString())
        }
        if (!inflatedView.etFourteenthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etFourteenthPlayer.text.toString())
        }
        if (!inflatedView.etFifteenthPlayer.text.isNullOrBlank()) {
            playerList.add(inflatedView.etFifteenthPlayer.text.toString())
        }
        onButtonPressed(playerList, Integer.decode(mParam1))
    }

    /**
     * Invoke this function to pass the data to
     * @param uri i.e list of player
     * @param teamType i.e main team or opponent Team {its integer value :-P }
     */
    fun onButtonPressed(uri: Any?, teamType: Int) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri, teamType)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    companion object {
        val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment IndividualTeamFragment.
         */
        fun newInstance(param1: String): IndividualTeamFragment {
            val fragment = IndividualTeamFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}
