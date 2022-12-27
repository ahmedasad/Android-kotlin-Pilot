package com.example.baseproject.ui.fragments.examplefragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.baseproject.R
import com.example.baseproject.adapter.SimpleListAdapter
import com.example.baseproject.base.BaseFragment
import com.example.baseproject.data.network.Result
import com.example.baseproject.databinding.FragmentBlankBinding
import com.example.baseproject.databinding.RowEventBinding
import com.example.baseproject.data.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : BaseFragment<FragmentBlankBinding>() {

    private lateinit var binding: FragmentBlankBinding
    private val viewModel: BlankViewModel by viewModel()
    private lateinit var adapter: SimpleListAdapter<RowEventBinding, User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    override fun getViewLayout(): Int = R.layout.fragment_blank

    override fun setView() {
        viewModel.item.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {}
                is Result.Success -> {
                    Toast.makeText(context, it.data.size.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {}
            }
        })
        adapter = SimpleListAdapter(R.layout.row_event)
        binding.recyclerView.adapter = adapter
//        adapter.updateContent(l)
    }

    override fun injectBinding(view: View): FragmentBlankBinding = DataBindingUtil.bind(view)!!


}