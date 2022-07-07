package work.racka.thinkrchive.v2.common.features.list.di

import org.koin.core.KoinApplication
import org.koin.dsl.module
import work.racka.common.mvvm.koin.vm.commonViewModel
import work.racka.thinkrchive.v2.common.features.list.container.ThinkpadListViewModel

object ThinkpadList {

    fun KoinApplication.listModules() =
        this.apply {
            modules(
                commonModule()
            )
        }

    private fun commonModule() = module {
        commonViewModel {
            ThinkpadListViewModel(
                helper = get(),
                settingsRepo = get()
            )
        }
    }
}