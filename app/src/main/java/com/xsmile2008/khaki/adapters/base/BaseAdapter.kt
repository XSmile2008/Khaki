package com.xsmile2008.khaki.adapters.base

import android.support.v7.widget.RecyclerView

import java.util.ArrayList

/**
 * Created by vladstarikov on 03.06.16.
 */
abstract class BaseAdapter<T, H : RecyclerView.ViewHolder>(items: Collection<T>? = null) : RecyclerView.Adapter<H>(), SimpleList<T> {

    private val mItems: MutableList<T> = if (items != null) ArrayList(items) else ArrayList()

    fun getItems(): List<T> = mItems

    fun setItems(items: Collection<T>?) {
        mItems.clear()
        items?.let { mItems.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mItems.size

    override fun add(item: T) {
        mItems.add(item)
        notifyItemInserted(getRealPos(mItems.size - 1))
    }

    override fun add(index: Int, item: T) {
        mItems.add(index, item)
        notifyItemInserted(getRealPos(index))
    }

    /**
     * @param items the collection of objects
     * @return true if data is modified, false otherwise (i.e. if the passed collection was empty).
     */
    override fun addAll(items: Collection<T>): Boolean {//TODO: super
        val oldSize = mItems.size
        return if (mItems.addAll(items)) {
            notifyItemRangeInserted(getRealPos(oldSize), items.size)//TODO: check this!
            true
        } else false
    }

    override fun set(index: Int, item: T): T {
        val old = mItems.set(index, item)
        notifyItemChanged(getRealPos(index))
        return old
    }

    override fun remove(index: Int): T {
        val old = mItems.removeAt(index)
        notifyItemRemoved(getRealPos(index))
        return old
    }

    override fun remove(item: T): Boolean {
        val index = mItems.indexOf(item)
        return if (index != NOT_FOUND) {
            mItems.removeAt(index)
            notifyItemRemoved(getRealPos(index))
            true
        } else false
    }

    override fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    override fun contains(item: T): Boolean = mItems.contains(item)

    override fun indexOf(item: T): Int = mItems.indexOf(item)

    override fun isEmpty(): Boolean = mItems.isEmpty()

    /**
     * @param relativePos pos in items array
     * @return adapter item position
     */
    fun getRealPos(relativePos: Int): Int = relativePos

    /**
     * @param realPos adapter item position
     * @return pos in items array
     */
    fun getRelativePos(realPos: Int): Int = realPos

    companion object {

        val NOT_FOUND = -1
    }
}