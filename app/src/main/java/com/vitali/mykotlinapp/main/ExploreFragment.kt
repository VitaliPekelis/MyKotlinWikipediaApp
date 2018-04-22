package com.vitali.mykotlinapp.main

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.models.WikiResult
import com.vitali.mykotlinapp.network.NetworkHandler
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ExploreFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ExploreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val adapter =  ArticleCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        search_card_view.setOnClickListener{
            listener?.onSearchClick()
        }

        explore_article_rv.layoutManager = LinearLayoutManager(context)
        explore_article_rv.adapter = adapter


        /*NetworkHandler.getSearch("Sentence", 0,3, 2).enqueue(object : Callback <WikiResult>{
            override fun onFailure(call: Call<WikiResult>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<WikiResult>?, response: Response<WikiResult>?) {

            }

        })*/


        NetworkHandler.getRandom(15).enqueue(object : Callback<WikiResult>{
            override fun onResponse(call: Call<WikiResult>?, response: Response<WikiResult>?) {
                val rootView = getView()
                rootView?.let {
                    val dataList = response?.body()?.query?.pages
                    if(dataList!=null)
                        adapter.currentData = dataList

                }
            }

            override fun onFailure(call: Call<WikiResult>?, t: Throwable?) {
                val rootView = getView()
                rootView?.let {
                    Snackbar.make(rootView,"An error occurred",Snackbar.LENGTH_SHORT).show()
                }
            }

        })

    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onExploreFragmentInteraction(/*uri*/)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
    interface OnFragmentInteractionListener {
        fun onExploreFragmentInteraction(/*uri: Uri*/)
        fun onSearchClick()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
                ExploreFragment().apply {
                    arguments = Bundle().apply {
                        /*putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)*/
                    }
                }
    }
}
