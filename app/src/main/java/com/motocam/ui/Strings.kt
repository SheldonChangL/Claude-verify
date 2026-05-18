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

    const val SETTINGS_TITLE = "設定"
    const val SETTINGS_GROUP_DEVICE = "裝置"
    const val SETTINGS_GROUP_LIVE = "即時畫面"
    const val SETTINGS_GROUP_ALERTS = "警示功能"
    const val SETTINGS_GROUP_EVENT_REC = "事件錄影"
    const val SETTINGS_GROUP_STORAGE = "儲存與錄影"
    const val SETTINGS_GROUP_FIRMWARE = "韌體與系統"
    const val SETTINGS_GROUP_VERSION = "版本資訊"

    const val SETTINGS_DEVICE_NAME = "MotoCam KC003"
    const val SETTINGS_DEVICE_FIRMWARE = "韌體版本 v2.1.4"
    const val SETTINGS_DEVICE_CONNECTED = "已連線"

    const val SETTINGS_STORAGE_LABEL = "儲存空間"
    const val SETTINGS_STORAGE_TOTAL = "10 GB"
    const val SETTINGS_STORAGE_USED = "已使用 4.2 GB"
    const val SETTINGS_STORAGE_FREE = "剩餘 5.8 GB"

    const val SETTINGS_DISPLAY_MODE = "顯示模式"
    const val SETTINGS_DISPLAY_LIVE = "即時影像"
    const val SETTINGS_DISPLAY_VIRTUAL = "虛擬場景"

    const val SETTINGS_POWERSAVE = "省電模式進入時間"
    const val SETTINGS_POWERSAVE_1 = "1 分"
    const val SETTINGS_POWERSAVE_3 = "3 分"
    const val SETTINGS_POWERSAVE_5 = "5 分"
    const val SETTINGS_POWERSAVE_ALWAYS = "恆亮"

    const val SETTINGS_BSD_ENABLE = "BSD 盲點偵測"
    const val SETTINGS_BSD_SENSITIVITY = "BSD 靈敏度"
    const val SETTINGS_RCW_ENABLE = "RCW 後方防撞"
    const val SETTINGS_RCW_SENSITIVITY = "RCW 靈敏度"
    const val SETTINGS_FALL_ENABLE = "傾倒偵測"

    const val SETTINGS_SENS_LOW = "低"
    const val SETTINGS_SENS_MID = "中"
    const val SETTINGS_SENS_HIGH = "高"

    const val SETTINGS_EVENT_PRE = "事件前錄影"
    const val SETTINGS_EVENT_PRE_VALUE = "5 秒"
    const val SETTINGS_EVENT_POST = "事件後錄影"
    const val SETTINGS_EVENT_POST_VALUE = "10 秒"

    const val SETTINGS_LOOP_CAP = "循環錄影容量上限"
    const val SETTINGS_LOOP_2G = "2 GB"
    const val SETTINGS_LOOP_4G = "4 GB"
    const val SETTINGS_LOOP_6G = "6 GB"
    const val SETTINGS_LOOP_8G = "8 GB"

    const val SETTINGS_FW_CHECK = "檢查韌體更新"
    const val SETTINGS_FW_AUTO = "自動更新"
    const val SETTINGS_FW_RESET = "回復原廠設定"

    const val SETTINGS_VERSION_APP = "App 版本"
    const val SETTINGS_VERSION_APP_VALUE = "1.0.0"
    const val SETTINGS_VERSION_DEVICE = "裝置韌體"
    const val SETTINGS_VERSION_DEVICE_VALUE = "v2.1.4"
}
