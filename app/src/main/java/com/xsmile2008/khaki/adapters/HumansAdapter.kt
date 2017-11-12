package com.xsmile2008.khaki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.adapters.base.BaseAdapter
import com.xsmile2008.khaki.adapters.base.BindableViewHolder
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.interfaces.OnItemPositionRemoved
import com.xsmile2008.khaki.interfaces.OnItemPositionSelected
import kotlinx.android.synthetic.main.item_human.view.*

/**
 * Created by vladstarikov on 11/11/17.
 */

class HumansAdapter(
        var onItemPositionSelected: OnItemPositionSelected<Human>? = null,
        var onItemPositionRemoved: OnItemPositionRemoved<Human>? = null
) : BaseAdapter<Human, BindableViewHolder<Human>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<Human> {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_human, parent, false))
    }

    override fun onBindViewHolder(holder: BindableViewHolder<Human>, position: Int) {
        holder.bind(getItems()[position])
    }

    private inner class Holder(itemView: View) : BindableViewHolder<Human>(itemView) {

        private val txtId = itemView.txt_id
        private val txtFirstName = itemView.txt_first_name
        private val txtMiddleName = itemView.txt_middle_name
        private val txtLastName = itemView.txt_last_name

        override fun bind(item: Human) {
            txtId.text = item.id.toString()
            txtFirstName.text = item.firstName
            txtMiddleName.text = item.middleName
            txtLastName.text = item.lastName

            itemView.setOnClickListener { onItemPositionSelected?.onSelected(getItems()[adapterPosition], adapterPosition) }
        }
    }
}
