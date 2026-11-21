package com.app.mailmanager.features.mailmanager.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.mailmanager.features.mailmanager.domain.MailRepository

class MailViewModel(
    private val repository: MailRepository
) : ViewModel() {

    private val _savedMail = MutableLiveData<String?>()
    val savedMail: LiveData<String?> = _savedMail

    init {
        refreshMail()
    }

    fun refreshMail() {
        _savedMail.value = repository.getSavedMail()
    }

    fun validateAndSaveMail(rawInput: String): ValidationResult {
        val cleaned = rawInput.trim()
        if (cleaned.isEmpty()) {
            return ValidationResult.Error("Please enter a Gmail address")
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cleaned).matches() ||
            !cleaned.lowercase().endsWith("@gmail.com")
        ) {
            return ValidationResult.Error("Please enter a valid @gmail.com address")
        }

        repository.saveMail(cleaned)
        _savedMail.value = cleaned
        return ValidationResult.Success
    }

    sealed class ValidationResult {
        object Success : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }

    class Factory(private val repository: MailRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MailViewModel(repository) as T
        }
    }
}
