package com.example.leadcapture.di

import com.example.leadcapture.presentation.leadform.LeadFormViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LeadFormViewModel)
}