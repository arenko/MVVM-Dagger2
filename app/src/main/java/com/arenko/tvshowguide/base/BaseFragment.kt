package com.arenko.tvshowguide.base

import android.content.Context

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    lateinit var baseContext: Context
        internal set

    @Override
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.baseContext = context
    }

    @Override
    override fun onDetach() {
        super.onDetach()
    }

    @Override
    override fun onDestroyView() {
        super.onDestroyView()

    }
}