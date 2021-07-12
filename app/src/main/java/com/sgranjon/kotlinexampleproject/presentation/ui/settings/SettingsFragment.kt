package com.sgranjon.kotlinexampleproject.presentation.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgranjon.kotlinexampleproject.databinding.FragmentSettingsBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment

class SettingsFragment : BaseVMFragment<SettingsViewModel, FragmentSettingsBinding>() {

    override val viewModelClass = SettingsViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate

}