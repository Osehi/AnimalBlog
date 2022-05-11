package com.polishone.animalblog.common.presentation

import com.polishone.animalblog.common.domain.model.AniBlog

data class HomeState(
    var isLoading: Boolean = false,
    var data: List<AniBlog>? = null,
    var error: String = ""
)
