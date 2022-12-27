package com.example.baseproject.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import rx.subjects.PublishSubject
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<VDB, M> :
    RecyclerView.Adapter<BaseListAdapter<VDB, M>.ViewHolder>() {

    var item: List<M> = ArrayList()
    val itemClickSubject: PublishSubject<M> = PublishSubject.create()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = item.size

    open fun updateContent(items: List<M>) {
        this.item = items
        notifyDataSetChanged()
    }

    internal fun ViewGroup.inflate(layoutRes: Int) =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding!!.root)
}