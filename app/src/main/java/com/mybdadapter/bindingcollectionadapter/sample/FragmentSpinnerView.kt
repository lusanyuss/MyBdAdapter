package com.mybdadapter.bindingcollectionadapter.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.bdAdapter.databinding.SpinnerViewBinding

class FragmentSpinnerView : Fragment() {
    private lateinit var viewModel: MutableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get()
        viewModel.setCheckable(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SpinnerViewBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.listeners = viewModel
            it.executePendingBindings()
        }.root
    }
}
