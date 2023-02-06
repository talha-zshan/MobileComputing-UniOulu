package com.example.talhamobilecomputing.ui.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.lifecycle.ViewModel
import com.example.talhamobilecomputing.data.entity.Option
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel: ViewModel() {
    private val _state = MutableStateFlow(ProfileViewState())
    val state: StateFlow<ProfileViewState>
        get() = _state

    private val _selectedOption = MutableStateFlow<Option?>(null)

    fun onOptionSelected(option: Option) {
        _selectedOption.value = option
    }
}


data class ProfileViewState(
    val optionsList: ArrayList<Option> = prepareOptionsData(),
    val selectedOption: Option? = null
)

private fun prepareOptionsData(): ArrayList<Option> {

    val appIcons = Icons.Outlined
    val optionsList = ArrayList<Option>()

    optionsList.add(
        Option(
            icon = appIcons.Person,
            title = "Account",
            subTitle = "Manage your account"
        )
    )

    optionsList.add(
        Option(
            icon = appIcons.Settings,
            title = "Settings",
            subTitle = "App notification settings"
        )
    )

    optionsList.add(
        Option(
            icon = appIcons.Info,
            title = "Help Center",
            subTitle = "FAQs and customer support"
        )
    )

    return optionsList
}
