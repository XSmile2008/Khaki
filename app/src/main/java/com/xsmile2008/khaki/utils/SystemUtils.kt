package com.xsmile2008.khaki.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by vladstarikov on 11/12/17.
 */

private val dateFormat = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())

fun formatDate(date: Date): String = dateFormat.format(date)