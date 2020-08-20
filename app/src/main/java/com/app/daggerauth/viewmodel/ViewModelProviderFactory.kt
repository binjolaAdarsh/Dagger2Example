package com.app.daggerauth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]
        if (creator == null) { // if the viewmodel has not been created
            for ((key, value) in creators) {

                // if its allowed ,set the provider <ViewModel>
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        // if this is not one of the allowed keys
        requireNotNull(creator) {
            "unknown model class $modelClass"
        }
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw  RuntimeException(e)
        }
    }

    companion object {
        private const val TAG = "ViewModelProviderFactor"
    }

}