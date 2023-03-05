package com.roseFinancials.lenafx.utils

import androidx.lifecycle.ViewModel

abstract class ResetViewModel: ViewModel() {
    abstract fun resetState()
}