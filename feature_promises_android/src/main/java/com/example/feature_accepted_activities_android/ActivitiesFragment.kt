package com.example.feature_accepted_activities_android

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_accepted_activities.AcceptedActivitiesStore
import com.example.feature_accepted_activities_android.adapter.ActivitiesAdapter
import com.example.feature_accepted_activities_android.adapter.ActivityModel
import com.example.navigation.NavigationScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.sharedGraphViewModel

class ActivitiesFragment : BaseFragment(R.layout.fragment_accepted_activities) {

    private val viewModel: ActivitiesViewModel by sharedGraphViewModel(NavigationScope.FRAME_SCOPE)

    private val list: RecyclerView by lazy { requireView().findViewById(R.id.activities_list_id) }
    private val adapter: ActivitiesAdapter by lazy { ActivitiesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(activitiesFeatureAndroidModuleDI)

        list.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: AcceptedActivitiesStore.State) {
        lifecycleScope.launchWhenCreated {
            val models = withContext(Default) { state.activities.map { ActivityModel(text = it.activity) } }
            adapter.set(models)
        }
    }

    override fun onDestroyView() {
        unloadKoinModules(activitiesFeatureAndroidModuleDI)
        super.onDestroyView()
    }
}