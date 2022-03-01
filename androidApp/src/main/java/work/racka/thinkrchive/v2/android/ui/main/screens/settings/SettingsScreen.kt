package work.racka.thinkrchive.v2.android.ui.main.screens.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.inject
import work.racka.thinkrchive.v2.android.ui.main.screens.ThinkrchiveScreens
import work.racka.thinkrchive.v2.android.utils.scaleInEnterTransition
import work.racka.thinkrchive.v2.android.utils.scaleInPopEnterTransition
import work.racka.thinkrchive.v2.android.utils.scaleOutExitTransition
import work.racka.thinkrchive.v2.android.utils.scaleOutPopExitTransition
import work.racka.thinkrchive.v2.common.integration.containers.settings.AppSettings

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun NavGraphBuilder.SettingsScreen(
    navController: NavHostController
) {

    composable(
        route = ThinkrchiveScreens.ThinkpadSettingsScreen.name,
        enterTransition = {
            scaleInEnterTransition()
        },
        exitTransition = {
            scaleOutExitTransition()
        },
        popEnterTransition = {
            scaleInPopEnterTransition()
        },
        popExitTransition = {
            scaleOutPopExitTransition()
        }
    ) {

        val settings: AppSettings by inject()
        val state by settings.state.collectAsState()

        SettingsScreenUI(
            currentTheme = state.themeValue,
            currentSortOption = state.sortValue,
            onThemeOptionClicked = {
                settings.host.saveThemeSettings(it)
                //viewModel.saveThemeSetting(it)
            },
            onSortOptionClicked = {
                settings.host.saveSortSettings(it)
                //viewModel.saveSortOptionSetting(it)
            },
            onBackButtonPressed = {
                navController.popBackStack()
            }
        )
    }
}