package com.motocam.ui.screens.home

import com.motocam.ui.Strings

enum class AlertChipKind { BSD_LEFT, BSD_RIGHT, RCW, TILT, CLEAR }

enum class AlertBannerKind { BSD, RCW, FALL }

enum class BsdSideKind { BSD, RCW }

data class BsdSideOverlayState(
    val leftVisible: Boolean = false,
    val rightVisible: Boolean = false,
    val kind: BsdSideKind = BsdSideKind.BSD,
)

data class FallOverlayState(
    val visible: Boolean = false,
    val title: String = Strings.FALL_TITLE,
    val angleDeg: Int = 43,
    val durationSec: Float = 3.2f,
    val savingLabel: String = Strings.FALL_SAVING,
)

data class AlertChip(
    val kind: AlertChipKind,
    val label: String,
)

data class ImageQualityState(
    val title: String = Strings.IQ_TITLE,
    val brightness: Int = 0,
    val contrast: Int = 50,
    val saturation: Int = 50,
    val sharpness: Int = 50,
    val expanded: Boolean = false,
)

data class AlertBannerState(
    val visible: Boolean,
    val kind: AlertBannerKind,
    val title: String,
    val subtitle: String,
    val time: String,
)

data class CameraOverlayState(
    val recording: Boolean,
    val recBadgeText: String,
    val timestamp: String,
    val tiltAngleDeg: Int? = null,
    val tiltDurationSec: Float? = null,
)

data class HomeUiState(
    val clock: String,
    val recording: Boolean,
    val recIndicatorText: String,
    val speedKmh: Int,
    val speedUnit: String,
    val camera: CameraOverlayState,
    val banner: AlertBannerState,
    val imageQuality: ImageQualityState,
    val reminderText: String,
    val alertChips: List<AlertChip>,
    val bsdSide: BsdSideOverlayState = BsdSideOverlayState(),
    val fallOverlay: FallOverlayState = FallOverlayState(),
) {
    companion object {
        fun mock(): HomeUiState = HomeUiState(
            clock = Strings.CLOCK_DEFAULT,
            recording = false,
            recIndicatorText = Strings.REC_IDLE,
            speedKmh = 32,
            speedUnit = Strings.SPEED_UNIT,
            camera = CameraOverlayState(
                recording = false,
                recBadgeText = Strings.REC_IDLE,
                timestamp = Strings.TIMESTAMP_DEFAULT,
            ),
            banner = AlertBannerState(
                visible = false,
                kind = AlertBannerKind.BSD,
                title = Strings.BANNER_BSD_TITLE,
                subtitle = Strings.BANNER_BSD_SUB_LEFT,
                time = Strings.BANNER_TIME_DEFAULT,
            ),
            imageQuality = ImageQualityState(),
            reminderText = Strings.REMINDER_RECORDING_OFF,
            alertChips = listOf(
                AlertChip(AlertChipKind.BSD_LEFT, Strings.ALERT_BSD_LEFT),
                AlertChip(AlertChipKind.BSD_RIGHT, Strings.ALERT_BSD_RIGHT),
                AlertChip(AlertChipKind.RCW, Strings.ALERT_RCW),
                AlertChip(AlertChipKind.TILT, Strings.ALERT_TILT),
                AlertChip(AlertChipKind.CLEAR, Strings.ALERT_CLEAR),
            ),
        )
    }
}
