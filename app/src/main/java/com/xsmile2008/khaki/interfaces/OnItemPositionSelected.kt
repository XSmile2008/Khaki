package com.xsmile2008.khaki.interfaces

/**
 * Created by vladstarikov on 11/12/17.
 */
interface OnItemPositionSelected<in T> {

    fun onSelected(item: T, pos: Int)
}