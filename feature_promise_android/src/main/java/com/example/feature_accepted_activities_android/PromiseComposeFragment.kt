package com.example.feature_accepted_activities_android

import androidx.compose.runtime.Composable
import com.example.feature_accepted_activities.promiseStoreModuleDI
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gubatenko.common_android.BaseComposeFragment

class PromiseComposeFragment : BaseComposeFragment() {

    private val viewModel: PromiseViewModel by viewModel()

    override val diModules = listOf(promiseStoreModuleDI, promiseFeatureAndroidModuleDI)

    @Composable
    override fun Screen() = PromiseCompose(viewModel)

}