package com.example.baseproject.ui.fragments.examplefragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.baseproject.R
import com.example.baseproject.adapter.SimpleListAdapter
import com.example.baseproject.base.BaseFragment
import com.example.baseproject.data.network.Result
import com.example.baseproject.databinding.FragmentBlankBinding
import com.example.baseproject.databinding.RowEventBinding
import com.example.baseproject.data.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BlankFragment : BaseFragment<FragmentBlankBinding>() {

    private lateinit var binding: FragmentBlankBinding

    val viewModel: BlankViewModel by viewModels()

    private lateinit var adapter: SimpleListAdapter<RowEventBinding, User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewLayout(): Int = R.layout.fragment_blank

    override fun setView() {
        viewModel.item.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {}
                is Result.Success -> {
                    Toast.makeText(context, it.data.size.toString(), Toast.LENGTH_SHORT).show()
                    adapter.updateContent(it.data)
                }
                is Result.Error -> {}
            }
        })
        adapter = SimpleListAdapter(R.layout.row_event)
        binding.recyclerView.adapter = adapter
    }

    override fun injectBinding(view: View): FragmentBlankBinding = DataBindingUtil.bind(view)!!


}