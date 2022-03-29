package work.racka.thinkrchive.v2.android.ui.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import domain.Thinkpad
import work.racka.thinkrchive.v2.android.R
import work.racka.thinkrchive.v2.android.ui.theme.Dimens
import work.racka.thinkrchive.v2.android.ui.theme.Shapes
import work.racka.thinkrchive.v2.android.ui.theme.ThinkRchiveTheme
import work.racka.thinkrchive.v2.android.utils.Constants

@Composable
fun ThinkpadFeatures(
    modifier: Modifier = Modifier,
    thinkpad: Thinkpad
) {
    //Scale animation
    val animatedProgress = remember {
        Animatable(initialValue = 0.7f)
    }
    LaunchedEffect(key1 = Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(500, easing = FastOutSlowInEasing)
        )
    }

    val animatedModifier = modifier
        .graphicsLayer(
            scaleX = animatedProgress.value,
            scaleY = animatedProgress.value
        )

    var maxLines by remember {
        mutableStateOf(1)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180F else 0F,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    Column(
        modifier = animatedModifier
            .fillMaxWidth()
            .padding(Dimens.MediumPadding.size)
    ) {
        Text(
            text = "Other Features & Details",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(
                    bottom = Dimens.MediumPadding.size,
                    start = Dimens.MediumPadding.size
                )
                .align(Alignment.Start)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = Shapes.large
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .padding(Dimens.MediumPadding.size)
            ) {
                SubtitleTextWithIcon(
                    subtitleName = "Screen Size",
                    subtitleData = thinkpad.screenSize,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.AspectRatio,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Touch Screen",
                    subtitleData = thinkpad.touchScreen,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.TouchApp,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Dual Battery",
                    subtitleData = thinkpad.dualBatt,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.BatteryChargingFull,
                    maxLines = maxLines
                )
                if (thinkpad.dualBatt.equals("Yes", ignoreCase = true)) {
                    SubtitleTextWithIcon(
                        subtitleName = "Internal Batt",
                        subtitleData = thinkpad.internalBatt,
                        style = MaterialTheme.typography.bodyLarge,
                        icon = Icons.Outlined.BatterySaver,
                        maxLines = maxLines
                    )
                }
                SubtitleTextWithIcon(
                    subtitleName = "Main Batt",
                    subtitleData = thinkpad.externalBatt,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.BatteryStd,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Fingerprint Reader",
                    subtitleData = thinkpad.fingerPrintReader,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Fingerprint,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Keyboard Type",
                    subtitleData = thinkpad.kbType,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.KeyboardAlt,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Backlit Keyboard",
                    subtitleData = thinkpad.backlitKb,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.WbTwilight,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Current BIOS",
                    subtitleData = thinkpad.biosVersion,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.SettingsApplications,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "BIOS Restrictions",
                    subtitleData = thinkpad.biosLockIn,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.Lock,
                    maxLines = maxLines
                )
                SubtitleTextWithIcon(
                    subtitleName = "Ports",
                    subtitleData = thinkpad.ports,
                    style = MaterialTheme.typography.bodyLarge,
                    icon = Icons.Outlined.SettingsInputHdmi,
                    maxLines = maxLines
                )
            }

            IconButton(
                onClick = {
                    maxLines = if (maxLines == 1) {
                        expanded = true
                        Int.MAX_VALUE
                    } else {
                        expanded = false
                        1
                    }
                },
                modifier = Modifier
                    .padding(
                        top = Dimens.SmallPadding.size,
                        end = Dimens.SmallPadding.size
                    )
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ExpandMore,
                    contentDescription = stringResource(id = R.string.expand_icon),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.rotate(angle)
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun ThinkpadDetailsPreview() {
    ThinkRchiveTheme {
        ThinkpadFeatures(thinkpad = Constants.ThinkpadsListPreview[0])
    }
}