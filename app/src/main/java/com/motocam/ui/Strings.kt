package com.motocam.ui

object Strings {
    const val REC = "REC"
    const val REC_IDLE = "未錄影"
    const val SPEED_UNIT = "km/h"

    const val ALERT_BSD_LEFT = "BSD 左"
    const val ALERT_BSD_RIGHT = "BSD 右"
    const val ALERT_RCW = "RCW"
    const val ALERT_TILT = "傾倒"
    const val ALERT_CLEAR = "清除"

    const val IQ_TITLE = "畫質調整"
    const val IQ_BRIGHTNESS = "亮度"
    const val IQ_CONTRAST = "對比"
    const val IQ_SATURATION = "飽和度"
    const val IQ_SHARPNESS = "銳利度"
    const val IQ_RESET = "恢復預設"

    const val REMINDER_RECORDING_OFF = "提醒：目前未啟動錄影，請留意。"
    const val REMINDER_BSD_OFF = "提醒：目前未啟動 BSD 事件錄影，請留意。"
    const val REMINDER_RCW_OFF = "提醒：目前未啟動 RCW 事件錄影，請留意。"

    const val SHUTTER_DESC = "截圖"
    const val FULLSCREEN_DESC = "全螢幕"
    const val FULLSCREEN_GLYPH = "⛶"
    const val ACCORDION_COLLAPSED_GLYPH = "▸"

    const val CLOCK_DEFAULT = "21:54"
    const val TIMESTAMP_DEFAULT = "2026-05-14 21:54:11"
    const val BANNER_TIME_DEFAULT = "21:34"
    const val IQ_SUBTITLE = "亮度 / 對比 / 飽和度 / 銳利度"

    const val BANNER_BSD_TITLE = "盲點警示 BSD"
    const val BANNER_BSD_SUB_LEFT = "左後方偵測到車輛接近"
    const val BANNER_BSD_SUB_RIGHT = "右後方偵測到車輛接近"
    const val BANNER_BSD_SUB_BOTH = "兩側同時偵測到車輛"
    const val BANNER_RCW_TITLE = "後方防撞警示 RCW"
    const val BANNER_RCW_SUB_BOTH = "正後方車輛快速接近，請注意"
    const val BANNER_FALL_TITLE = "異常傾倒警示 FALL"

    const val FALL_TITLE = "車輛傾倒 FALL"
    const val FALL_ANGLE_PREFIX = "傾角"
    const val FALL_DURATION_PREFIX = "持續"
    const val FALL_SAVING = "保護錄影紀錄中…"

    const val TAB_HOME = "首頁"
    const val TAB_ALERTS = "觸發事件"
    const val TAB_PLAYBACK = "回放"
    const val TAB_SETTINGS = "設定"

    const val PLAYBACK_TITLE = "回放"
    const val PLAYBACK_FILTER_ALL = "全部 12"
    const val PLAYBACK_FILTER_BSD = "BSD …"
    const val PLAYBACK_FILTER_SELECT = "選取"
    const val PLAYBACK_DAY_HEADER = "今天 · 2026-05-14"

    const val TAG_BSD = "BSD"
    const val TAG_RCW = "RCW"
    const val TAG_FALL = "傾倒"
    const val TAG_SHOT = "截圖"

    const val ALERTS_TITLE = "觸發事件"
    const val ALERTS_FILTER_ALL = "全部 10"
    const val ALERTS_FILTER_BSD = "BSD …"
    const val ALERTS_DAY_HEADER = "今天 · 2026-05-14"

    const val ALERT_TITLE_BSD_LEFT = "左側盲點警示"
    const val ALERT_TITLE_BSD_RIGHT = "右側盲點警示"
    const val ALERT_TITLE_RCW = "後方防撞警示"
    const val ALERT_TITLE_FALL = "車輛傾倒偵測"
}
