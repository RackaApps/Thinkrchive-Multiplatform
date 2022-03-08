package work.racka.thinkrchive.v2.common.integration.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import work.racka.thinkrchive.v2.common.integration.containers.details.ThinkpadDetailsContainerHost
import work.racka.thinkrchive.v2.common.integration.containers.details.ThinkpadDetailsContainerHostImpl
import work.racka.thinkrchive.v2.common.integration.repository.ThinkrchiveRepository

actual class ThinkpadDetailsViewModel(
    repository: ThinkrchiveRepository
) : ViewModel() {

    val host: ThinkpadDetailsContainerHost = ThinkpadDetailsContainerHostImpl(
        repository = repository,
        scope = viewModelScope
    )
}