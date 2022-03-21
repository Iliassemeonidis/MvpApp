package com.example.mvpapplicaton.view.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpapplicaton.databinding.ItemDetailsViewBinding
import com.example.mvpapplicaton.view.main.adapter.IUserListPresenterDetails

class DetailsAdapter(private val presenter: IUserListPresenterDetails) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemDetailsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemDetailsViewBinding) :
        RecyclerView.ViewHolder(vb.root), DetailItemView {

        override var pos: Int = -1

        override fun setRepo(repo: String) = with(vb) {
            tvRep.text = repo
        }
    }
}