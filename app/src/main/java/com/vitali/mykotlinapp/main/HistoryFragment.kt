package com.vitali.mykotlinapp.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vitali.mykotlinapp.R
import com.vitali.mykotlinapp.db.DataBaseAsyncTask
import com.vitali.mykotlinapp.db.WikiDatabase
import com.vitali.mykotlinapp.models.WikiPage
import kotlinx.android.synthetic.main.fragment_history.*

/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HistoryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HistoryFragment : Fragment(), IAdapterListener
{

    // TODO: Rename and change types of parameters
    /*private var param1: String? = null
    private var param2: String? = null*/
    private var listener: OnFragmentInteractionListener? = null
    private val mAdapter = ArticleCardAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            /*param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        history_article_rv.layoutManager = LinearLayoutManager(context)
        history_article_rv.adapter = mAdapter
    }

    override fun onResume()
    {
        super.onResume()
        fetchHistories()
    }

    private fun fetchHistories()
    {
        context?.let {
            DataBaseAsyncTask(object : DataBaseAsyncTask.IExecutor<ArrayList<WikiPage>>
            {
                override fun doInBackground(): ArrayList<WikiPage>
                {
                    return WikiDatabase.getInstance(it).allHistories()
                }

                override fun onPostExecute(response: ArrayList<WikiPage>)
                {
                    mAdapter.currentData.clear()
                    mAdapter.currentData = response

                    mAdapter.notifyDataSetChanged()
                }
            }).execute()
        }
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
    override fun clickOnItem(page: WikiPage)
    {
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
                HistoryFragment().apply {
                    arguments = Bundle().apply {
                        /* putString(ARG_PARAM1, param1)
                         putString(ARG_PARAM2, param2)*/
                    }
                }
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
        fun onHistoryFragmentInteraction(/*uri: Uri*/)
    }
}
