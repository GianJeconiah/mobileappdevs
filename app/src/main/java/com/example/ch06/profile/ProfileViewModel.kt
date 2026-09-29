package com.example.ch06.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    private val profile = repository.getProfile()
    private val _uiState = MutableStateFlow(
        ProfileUiState(
            username = profile.username,
            notificationsEnabled = profile.notificationsEnabled
        )
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onUsernameChange(newUsername: String) {
        repository.updateUsername(newUsername)
        _uiState.update { it.copy(username = newUsername) }
    }

    fun onToggleNotification(enabled: Boolean) {
        repository.toggleNotification(enabled)
        _uiState.update { it.copy(notificationsEnabled = enabled) }
    }
}
