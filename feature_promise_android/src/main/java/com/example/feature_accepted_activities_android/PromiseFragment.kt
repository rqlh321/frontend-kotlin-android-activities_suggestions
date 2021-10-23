package com.example.feature_accepted_activities_android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_accepted_activities.PromiseStore
import com.example.feature_accepted_activities.promiseStoreModuleDI
import com.example.feature_accepted_activities_android.adapter.PromiseAdapter
import com.example.feature_accepted_activities_android.adapter.PromiseItemDecorator
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gubatenko.common_android.BaseFragment

class PromiseFragment : BaseFragment(R.layout.fragment_promise) {

    private val viewModel: PromiseViewModel by viewModel()

    private val list: RecyclerView by lazy { requireView().findViewById(R.id.promise_list_id) }
    private val info: TextView by lazy { requireView().findViewById(R.id.info_text_id) }
    private val adapter: PromiseAdapter by lazy { PromiseAdapter() }

    override val diModules = listOf(promiseStoreModuleDI, promiseFeatureAndroidModuleDI)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.adapter = adapter
        val offset = resources.getDimension(R.dimen.offset_medium).toInt()
        list.addItemDecoration(PromiseItemDecorator(offset))
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: PromiseStore.State) {
        adapter.set(state.promiseList)
        list.isVisible = state.isPromiseListVisible
        info.text = state.infoText
        info.isVisible = state.isInfoTextVisible
    }

}