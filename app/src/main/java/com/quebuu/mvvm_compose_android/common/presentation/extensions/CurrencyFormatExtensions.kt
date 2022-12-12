package com.quebuu.mvvm_compose_android.common.presentation.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

private const val DECIMAL_FORMAT = "#,##0.00"
private const val DECIMAL_SEPARATOR = '.'
private const val GROUPING_SEPARATOR = ','

private val currencyFormat = DecimalFormat(DECIMAL_FORMAT, DecimalFormatSymbols().apply {
    decimalSeparator = DECIMAL_SEPARATOR
    groupingSeparator = GROUPING_SEPARATOR
})

fun Double.formatAsCurrency(): String = currencyFormat.format(this)