# Story 11.1 — Popup / Empty / Loading State 盤點

> Issue: SheldonChangL/Claude-verify#33
> Requirement IDs: FR-7, OPS-5
> 解除 PRD Open Question #1

## 掃描範圍

- `.design-source/html/` 內所有 HTML 檔
  - `index.html`（825 行；mirror 唯一 HTML，所有路由皆以此為來源）

> mirror 目錄僅含單一 `index.html`，原設計站以 CSS class 切換 `liveview / playback / alerts / settings` 視圖，並無另外的 `*.html`。下列盤點即覆蓋全部路由。

## 結論摘要

| 類別 | 件數 | 實作 | 略過 |
|---|---|---|---|
| 彈窗 / Overlay | 4 | 3 | 1 |
| 空狀態 / Empty State | 1 | 1 | 0 |
| Loading / 進行中 State | 2 | 2 | 0 |

mirror HTML **沒有** 傳統 Modal Dialog（`role="dialog"` / `.modal`）、Toast、Snackbar、Skeleton Loader 或全屏 Spinner — 整個設計以「in-frame overlay + 文案輪播」表達狀態，不另開視窗。此結論可作為解除 PRD Open Question #1 的依據。

## Checklist

### 彈窗 / Overlay 類

- [x] **`alert-banner`**（`index.html:191–209, 593`）— BSD / RCW / tilt 三種色票的浮動橫幅，位於 hero 與 cam-feed 之間。
  - 狀態：**實作**（Story 5.x HomeScreen 1:1 mirror 已涵蓋 BSD / RCW；tilt 變體沿用同一 composable 切色票）。
- [x] **`fall-overlay`**（`index.html:228–249, 512–528`）— 車輛傾倒全 cam-feed in-frame 警示，含 icon / 標題 / 傾角 / 持續時間 / 「保護錄影紀錄中…」按鈕。
  - 狀態：**實作**（HomeScreen mirror 已含；FR-7 要求覆蓋）。
- [x] **`bsd-overlay` L / R**（`index.html:177–187, 393–394`）— 左右側邊發光條紋，BSD 偵測時雙側亮起、RCW 時染紅。
  - 狀態：**實作**（HomeScreen mirror 內以漸層 Box 還原）。
- [ ] **`demo-wrap` 觸發鈕**（`index.html:348, 533–539`）— mirror 用於人工觸發 BSD / RCW / 傾倒 / 清除的 5 個 floating debug 按鈕。
  - 狀態：**略過**（設計來源即標註為 demo / testing 控制；對應 `# Design tokens » Forbidden additions` 中「NO standalone 測試按鈕 / demo controls」規則；正式 App 不還原）。

### 空狀態 / Empty State 類

- [x] **`gps-bar` 提醒輪播**（`index.html:258–266, 573–579`）— 當未啟動錄影 / BSD 錄影 / RCW 錄影時，每 N 秒輪播三段「目前未啟動 … 請留意」綠點提醒文字。實際上即為「沒有錄影中事件」的 empty-state 表達。
  - 狀態：**實作**（HomeScreen mirror 已實作 reminder strip）。

### Loading / 進行中 State 類

- [x] **`rec-badge` idle / REC**（`index.html:157–161, 494, 696`）— cam-feed 右上「未錄影 / REC」pill；REC 紅點脈動為錄影進行中狀態。
  - 狀態：**實作**（HomeScreen mirror 內 RecBadge composable）。
- [x] **`fall-btn-saving` 保護錄影紀錄中**（`index.html:248–249, 524–526`）— 傾倒事件觸發後底部按鈕呈現紅點閃爍 + 「保護錄影紀錄中…」，為事件錄影寫入進行中的 loading 表達。
  - 狀態：**實作**（隨 `fall-overlay` 一併在 HomeScreen mirror 還原）。

## 未發現的 state（明確負面盤點）

下列項目經逐節 grep 後 **mirror 內不存在**，後續實作不需為其建任何元件；若日後需要請開新 story 而非追加到本 milestone：

- Modal Dialog（含確認 / 警告 / 設定彈窗）— mirror 全無 `role="dialog"` 或 `.modal` / `.popup` class。
- Bottom Sheet / Action Sheet — 無 `.sheet` / `.drawer` 元件。
- Toast / Snackbar — 無對應 class 或 JS API。
- Skeleton Loader / Shimmer Placeholder — Playback / Alerts 清單為靜態硬編資料，無 loading 佔位列。
- 全屏 Spinner / Progress Indicator — 僅有 Settings 的 `儲存空間` 水平 progress bar，屬資料視覺化而非 loading state。
- Empty list state — Playback / Alerts mirror 皆內含 ≥ 10 筆假資料，無「無紀錄」/「無事件」空畫面圖文。

## 簽核

- 盤點作者：autonomous implementation engineer (Claude)
- 盤點日期：2026-05-18
- 依據檔案：`.design-source/html/index.html` @ commit `86269a3`
- 狀態：**待 [HUMAN] 覆核**（Issue 標題標註 [HUMAN]；本文件提供候選盤點，最終認可需由 PO 在 PR 上勾選確認）。
