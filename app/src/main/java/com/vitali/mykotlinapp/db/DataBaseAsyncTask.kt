package com.vitali.mykotlinapp.db

import android.os.AsyncTask

class DataBaseAsyncTask<T>(private val executor: IExecutor<T>) : AsyncTask<Void, Void, T>()
{

    override fun doInBackground(vararg voids: Void): T
    {
        return executor.doInBackground()
    }

    override fun onPostExecute(value: T)
    {
        executor.onPostExecute(value)
    }

    interface IExecutor<T>
    {
        fun doInBackground(): T
        fun onPostExecute(response: T)
    }
}