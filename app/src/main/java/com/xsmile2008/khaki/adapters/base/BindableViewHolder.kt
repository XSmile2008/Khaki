package com.xsmile2008.khaki.adapters.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by vladstarikov on 11/11/17.
 */

abstract class BindableViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}
