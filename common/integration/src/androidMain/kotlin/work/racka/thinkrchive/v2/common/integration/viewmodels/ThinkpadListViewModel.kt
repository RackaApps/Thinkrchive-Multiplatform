package work.racka.thinkrchive.v2.common.integration.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import work.racka.thinkrchive.v2.common.integration.containers.list.ThinkpadListContainerHost
import work.racka.thinkrchive.v2.common.integration.containers.list.ThinkpadListContainerHostImpl
import work.racka.thinkrchive.v2.common.integration.containers.list.ThinkpadListHelper
import work.racka.thinkrchive.v2.common.integration.containers.settings.AppSettings

actual class ThinkpadListViewModel(
    helper: ThinkpadListHelper,
    backgroundDispatcher: CoroutineDispatcher = Dispatchers.Default,
    settings: AppSettings,
) : ViewModel() {

    val host: ThinkpadListContainerHost = ThinkpadListContainerHostImpl(
        helper = helper,
        backgroundDispatcher = backgroundDispatcher,
        settings = settings,
        scope = viewModelScope
    )
}
