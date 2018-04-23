package com.vitali.mykotlinapp.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.articledetails.ArticleDetailActivity
import com.vitali.mykotlinapp.global.AppConstants
import kotlinx.android.synthetic.main.fragment_favorites.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoritesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FavoritesFragment : Fragment(), IAdapterListener
{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        favorites_article_rv.layoutManager = LinearLayoutManager(context)
        favorites_article_rv.adapter = SearchArticlesAdapter(this)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri)
    {
        listener?.onFavoritesFragmentInteraction(/*uri*/)
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener)
        {
            listener = context
        }
        else
        {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach()
    {
        super.onDetach()
        listener = null
    }

    //----------------------------------------------------------------------------------------------
    // IAdapterListener - implementation
    //----------------------------------------------------------------------------------------------
    override fun clickOnItem(url: String)
    {
        val intent = Intent(context, ArticleDetailActivity::class.java)
        intent.putExtra(AppConstants.URL_EXTRA, url)
        startActivity(intent)
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        fun onFavoritesFragmentInteraction(/*uri: Uri*/)
    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
                FavoritesFragment().apply {
                    arguments = Bundle().apply {
                        /*putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)*/
                    }
                }
    }
}
