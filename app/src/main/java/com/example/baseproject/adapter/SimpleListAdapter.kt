package com.example.baseproject.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.baseproject.BR
import com.example.baseproject.base.BaseListAdapter
import com.jakewharton.rxbinding.view.RxView

open class SimpleListAdapter<VDB, M>(@LayoutRes private val resIdRes: Int) :
    BaseListAdapter<VDB, M>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(parent.inflate(resIdRes))
        val holder = ViewHolder(viewDataBinding!!)
        RxView.clicks(viewDataBinding!!.root)
            .takeUntil(RxView.detaches(parent))
            .subscribe {
                val model = item[holder.adapterPosition]
                itemClickSubject.onNext(model)
            }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.setVariable(BR.model, item[position])
        super.onBindViewHolder(holder, position)
    }

}