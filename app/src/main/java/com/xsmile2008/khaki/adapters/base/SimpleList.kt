package com.xsmile2008.khaki.adapters.base

/**
 * Created by vladstarikov on 4/19/17.
 */

interface SimpleList<T> {

    fun add(item: T)

    fun add(index: Int, item: T)

    /**
     * @param items the collection of objects
     * @return true if data is modified, false otherwise (i.e. if the passed collection was empty).
     */
    fun addAll(items: Collection<T>): Boolean

    operator fun set(index: Int, item: T): T

    fun remove(index: Int): T

    fun remove(item: T): Boolean

    fun clear()

    operator fun contains(item: T): Boolean

    fun indexOf(item: T): Int

    fun isEmpty(): Boolean
}