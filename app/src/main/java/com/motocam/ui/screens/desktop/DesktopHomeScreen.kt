/*
 * DesktopHomeScreen — Desktop Home 寬版壓到 portrait 的裁切策略
 *
 * 議題：Story 10.1 — Desktop Home 裁切策略對齊（GitHub #31）
 * 對應 PRD Open Question #3 / FR-6 / OPS-5
 *
 * 參考資料：
 * - Pixel reference: backend/design_tokens/screens/desktop_home.png（desktop mirror）
 * - Mobile portrait reference: backend/design_tokens/screens/home.png、home_full.png
 *
 * 比對結論（Design tokens block 已明確標註）：
 *   "desktop mirror; ignore the surrounding device frame — content is identical to mobile"
 *   亦即 desktop_home.png 內部繪製的內容 = mobile portrait home，
 *   差異僅在「外層的 device frame / 桌面背景」。
 *
 * 裁切策略（[HUMAN] 已於 PR 確認）：
 *   1. 裁切範圍：去除 desktop_home.png 外層的桌面背景與裝置外框（device frame），
 *      只保留裝置螢幕區（content viewport）。
 *   2. 縮放比例：保留 mobile portrait 寬度（screen viewport 寬度 = mobile 寬度，1.0×），
 *      不放大、不額外壓縮——desktop 上的螢幕內容本身已是 portrait 比例。
 *   3. 保留區塊：與 mobile / HomeScreen.kt 完全相同——
 *      status bar、hero 速度數字、(條件式) alert-banner、camera viewport、
 *      畫質調整 accordion、reminder strip、shutter button、alert chip row、bottom-tab nav。
 *   4. 不另外為 desktop 製作 landscape / wide variant；Story 5.3 的 portrait
 *      實作即視為 desktop 的唯一渲染目標。
 *
 * 落地方式：本檔案僅作為策略註解的承載點，渲染交由 HomeScreen() 共用。
 * 解除 PRD Open Question #3，避免後續 desktop variant 的重工。
 */
package com.motocam.ui.screens.desktop

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.motocam.ui.screens.home.HomeScreen
import com.motocam.ui.theme.MotoTheme

/**
 * Story 10.2 — DesktopHomeScreen 壓縮 portrait 還原（GitHub #32）
 *
 * 依 Story 10.1 結論：desktop_home.png 去掉外層 device frame 後，
 * 內容與 mobile portrait HomeScreen 完全一致，因此本 composable 直接
 * 委派至 HomeScreen()，不在 MotoNavHost 註冊獨立 route——避免重複
 * navigation graph 與重工。
 *
 * Pixel diff 驗收參見：docs/qa/story-10.2-desktop-home-pixel-diff.md
 */
@Composable
fun DesktopHomeScreen() {
    HomeScreen()
}

@Preview(showBackground = true, backgroundColor = 0xFF06080F, widthDp = 390, heightDp = 844)
@Composable
private fun DesktopHomeScreenPreview() {
    MotoTheme {
        DesktopHomeScreen()
    }
}
