package work.racka.thinkrchive.v2.desktop.ui.navigation.components.home

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import states.list.ThinkpadListSideEffect
import states.list.ThinkpadListState
import work.racka.common.mvvm.koin.decompose.commonViewModel
import work.racka.thinkrchive.v2.common.all_features.list.viewmodel.ThinkpadListViewModel
import work.racka.thinkrchive.v2.desktop.ui.screens.splitpane.PaneConfig

class HomePaneComponentImpl(
    componentContext: ComponentContext,
    private val onSettingsClicked: () -> Unit,
    private val onAboutClicked: () -> Unit,
    private val onDonateClicked: () -> Unit,
) : HomePaneComponent, ComponentContext by componentContext {
    private val logger = Logger.withTag("HomePaneComponent").d { "Component created" }

    private val viewModel: ThinkpadListViewModel by commonViewModel()

    private val _paneState: MutableStateFlow<PaneConfig> = MutableStateFlow(PaneConfig.Blank)

    override val state: StateFlow<ThinkpadListState.State>
        get() = viewModel.state

    override val sideEffect: Flow<ThinkpadListSideEffect>
        get() = viewModel.sideEffect

    override val paneState: StateFlow<PaneConfig>
        get() = _paneState

    override fun updatePaneState(config: PaneConfig) {
        _paneState.value = config
    }

    override fun search(query: String) {
        viewModel.getSortedThinkpadList(query)
    }

    override fun sortOptionClicked(sort: Int) {
        viewModel.sortSelected(sort)
    }

    override fun settingsClicked() {
        onSettingsClicked()
    }

    override fun aboutClicked() {
        onAboutClicked()
    }

    override fun donateClicked() {
        onDonateClicked()
    }

    init {
        lifecycle.doOnDestroy {
            viewModel.destroy()
        }
    }
}