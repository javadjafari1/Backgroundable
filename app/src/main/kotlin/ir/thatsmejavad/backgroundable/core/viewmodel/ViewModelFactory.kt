package ir.thatsmejavad.backgroundable.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelFactory {
    fun <VM : ViewModel> create(
        modelClass: Class<VM>,
        handle: SavedStateHandle
    ): VM
}
