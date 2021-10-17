package ru.gubatenko.common_android

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.navigation.NavigationMain
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

fun View.onClick(callback: () -> Unit) = setOnClickListener { callback.invoke() }

inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy {
    val navGraphId = (requireActivity() as NavigationMain).frameGraphId()
    val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
    getKoin().getViewModel(ViewModelParameter(VM::class, qualifier, parameters, null, store, null))
}
