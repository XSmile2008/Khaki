package com.xsmile2008.khaki.interfaces

/**
 * Created by vladstarikov on 11/12/17.
 */
interface OnItemPositionRemoved<in T> {

    fun onRemoved(item: T, pos: Int)
}