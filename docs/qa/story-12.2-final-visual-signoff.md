# Story 12.2 — 最終視覺驗收 Worksheet

> Issue: https://github.com/SheldonChangL/Claude-verify/issues/36
> Requirement IDs: OPS-3, OPS-5, OPS-6, NFR-10
> Depends on: Story 10.2（Desktop Home portrait）、Story 11.2（popup/empty/loading composable）、Story 12.1（interactive state 盤點）

本檔案為整個專案的 **[HUMAN] 最終視覺 gate**：4 個主 screen + Desktop Home + Story 11.1「實作」清單列出的 7 個 state，須由 PO 親自於 emulator 截圖、合成 side-by-side、確認 diff ≤ 2px 後簽核，OPS-5 才視為達成、專案可宣告完工。

---

## 1. 收斂範圍

本 worksheet 為「截圖 + 簽核」型 gate，不再新增實作。截圖目標為前序 milestone 已完成的下列項目：

### 1.1 主 screen（4）

| Screen | Compose 進入點 | 設計基準 | 既有 pixel-diff worksheet |
|---|---|---|---|
| Home（/） | `HomeScreen.kt` | `.design-source/html/index.html` liveview 視圖 | `docs/qa/story-5.3-home-pixel-diff.md` |
| Playback（/playback） | `PlaybackScreen.kt` | `.design-source/html/index.html` playback 視圖 | `docs/qa/story-7.4-playback-pixel-diff.md` |
| Alerts（/alerts） | `AlertsScreen.kt` | `.design-source/html/index.html` alerts 視圖 | `docs/qa/story-8.4-alerts-pixel-diff.md` |
| Settings（/settings） | `SettingsScreen.kt` | `.design-source/html/index.html` settings 視圖 | `docs/qa/story-9.4-settings-pixel-diff.md` |

### 1.2 Desktop Home（1）

| Form factor | Compose 進入點 | 設計基準 | 既有 pixel-diff worksheet |
|---|---|---|---|
| Desktop（橫向裝置 → portrait 還原） | `DesktopHomeScreen.kt` | `.design-source/screens/desktop_home.png`（來源 `backend/design_tokens/screens/desktop_home.png`） | `docs/qa/story-10.2-desktop-home-pixel-diff.md` |

### 1.3 Story 11.1 列入「實作」的 state（7）

源自 `docs/qa/story-11.1-popup-empty-loading-inventory.md` Checklist 中標記為「實作」的項目：

| # | State | 所屬 screen | mirror 行號 | Compose 對應 |
|---|---|---|---|---|
| S-01 | `alert-banner`（BSD 左／右） | Home | 191–209, 593 | `HomeScreen.kt` AlertBanner（BSD 色票） |
| S-02 | `alert-banner`（RCW） | Home | 191–209, 593 | `HomeScreen.kt` AlertBanner（RCW 色票） |
| S-03 | `fall-overlay` | Home | 228–249, 512–528 | `HomeScreen.kt` FallOverlay |
| S-04 | `bsd-overlay` L / R 發光條紋 | Home | 177–187, 393–394 | `HomeScreen.kt` BsdGlow（漸層 Box） |
| S-05 | `gps-bar` 提醒輪播（empty state 表達） | Home | 258–266, 573–579 | `HomeScreen.kt` ReminderStrip |
| S-06 | `rec-badge` idle / REC | Home | 157–161, 494, 696 | `HomeScreen.kt` RecBadge |
| S-07 | `fall-btn-saving` 保護錄影紀錄中 | Home | 248–249, 524–526 | `HomeScreen.kt` FallOverlay > saving 按鈕 |

> 11.1 中標註「略過」的 `demo-wrap` 觸發鈕屬 Forbidden additions，本 gate 不要求截圖。

---

## 2. 截圖採集步驟

```bash
# 0. 前置
#    AVD：Pixel 6, API 35（Android 15）；參考 Story 5.3 / 7.4 / 8.4 / 9.4 / 10.2 已通過環境
./gradlew :app:installDebug

# 1. 落地目錄
mkdir -p docs/qa/artefacts/story-12.2

# 2. 主 screen 全 viewport（不滾動），逐張用 adb 截圖
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/app_home.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/app_playback.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/app_alerts.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/app_settings.png

# 3. Desktop Home portrait 還原
#    切到「Desktop 模式（橫向裝置 portrait 還原）」入口，截圖落地
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/app_desktop_home.png

# 4. Story 11.1 列入「實作」的 7 個 state（皆在 Home 上）
#    依 §1.3 表格觸發後截圖
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s01_alert_banner_bsd.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s02_alert_banner_rcw.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s03_fall_overlay.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s04_bsd_overlay_lr.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s05_gps_bar.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s06_rec_badge.png
adb exec-out screencap -p > docs/qa/artefacts/story-12.2/state_s07_fall_btn_saving.png
```

對應的 design 基準圖：
- 4 個主 screen 參考 `.design-source/screens/`（如不存在，沿用各前序 story 已落地的 `design_*.png`）。
- Desktop Home 參考 `backend/design_tokens/screens/desktop_home.png`（鏡像至 `.design-source/screens/desktop_home.png`）。
- 7 個 state 參考 `.design-source/html/index.html` 對應行號的 Chrome headless 抓圖。

---

## 3. Side-by-side 合成

每張上述截圖須對應一張左 design、右 app 的合成圖，落地至：

```
docs/qa/artefacts/story-12.2/side_by_side/
├── home.png              playback.png         alerts.png         settings.png
├── desktop_home.png
└── state_s01.png ... state_s07.png
```

合成工具：Preview / Figma / Photoshop 任一；或 ImageMagick：

```bash
# 範例：以 ImageMagick 合成 home 並量化 pixel diff
magick docs/qa/artefacts/story-5.3/design_home.png \
       docs/qa/artefacts/story-12.2/app_home.png +append \
       docs/qa/artefacts/story-12.2/side_by_side/home.png

# 量化：每張須 ≤ 2px AE（absolute error 像素數，依視覺特徵容忍 anti-aliasing）
magick compare -metric AE \
       docs/qa/artefacts/story-5.3/design_home.png \
       docs/qa/artefacts/story-12.2/app_home.png null: 2>&1 \
       | tee docs/qa/artefacts/story-12.2/diff_home.txt
```

> AE 數值若高於肉眼可察覺臨界（通常 > 數百 px 群聚於同一區塊），即視為超出 2px 標準。允許小範圍 anti-aliasing 散點。

---

## 4. 驗收 Checklist

### 4.1 主 screen × 4

- [ ] **Home**：與 Story 5.3 checklist 全項一致；hero 大號車速、cam-feed、畫質調整、提醒、shutter、tag chip row、bottom-tab 視覺 diff ≤ 2px。
- [ ] **Playback**：與 Story 7.4 checklist 全項一致；filter 三 chip、日期 section、video list 卡片內元素 diff ≤ 2px。
- [ ] **Alerts**：與 Story 8.4 checklist 全項一致；filter chip、event row（icon tile + title + tag + thumbnail）diff ≤ 2px。
- [ ] **Settings**：與 Story 9.4 checklist 全項一致；裝置卡、儲存卡、segmented selector、警示功能 toggle 群 diff ≤ 2px。

### 4.2 Desktop Home

- [ ] 與 Story 10.2 checklist 全項一致；portrait 還原裁切策略正確，hero 速度數字 + cam-feed 比例與 `desktop_home.png` 對齊 diff ≤ 2px。

### 4.3 Story 11.1 「實作」7 state

- [ ] **S-01 alert-banner BSD**：左／右色票（accent #C8FF00）位置與字級對齊。
- [ ] **S-02 alert-banner RCW**：紅色票色票與圖示對齊。
- [ ] **S-03 fall-overlay**：cam-feed in-frame 全覆蓋，icon / 標題 / 傾角 / 持續時間 / 「保護錄影紀錄中…」按鈕齊全。
- [ ] **S-04 bsd-overlay L/R**：左右側發光條紋色票與漸層方向對齊；RCW 時染紅符合 mirror。
- [ ] **S-05 gps-bar 提醒輪播**：綠點 + 三段輪播文案順序與字級對齊；mirror 為 empty state 表達。
- [ ] **S-06 rec-badge**：idle = 「未錄影」灰 pill；REC = 紅點脈動 + 「REC」白字。
- [ ] **S-07 fall-btn-saving**：傾倒 overlay 底部按鈕 → 紅點閃爍 + 「保護錄影紀錄中…」。

### 4.4 Forbidden additions 全域複檢

- [ ] Home：未出現 快捷控制 / 測試按鈕 / GPS 狀態 卡 / 設定捷徑 / 天氣 / map / 速度歷史圖；alert tag row 為小 chip 而非大型按鈕。
- [ ] Playback：未出現 grid toggle / sort dropdown / search bar / FAB。
- [ ] Alerts：未出現 map / 「標記為已讀」 bulk bar / FAB。
- [ ] Settings：未出現 帳號 / 主題切換 / 語系切換 / 頂端 關於 / 紅色 重設.

---

## 5. [HUMAN] 簽核

| 範圍 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| 4 個主 screen diff ≤ 2px | ☐ 通過 / ☐ 待修 |  |  |
| Desktop Home diff ≤ 2px | ☐ 通過 / ☐ 待修 |  |  |
| Story 11.1 列入「實作」7 state diff ≤ 2px | ☐ 通過 / ☐ 待修 |  |  |
| Forbidden additions 全域未出現 | ☐ 通過 / ☐ 待修 |  |  |
| **專案完工（OPS-5）簽核** | ☐ 通過 / ☐ 待修 |  |  |

> 五列皆「通過」後，Story 12.2 視為 Done，OPS-3 / OPS-5 / OPS-6 / NFR-10 一併解鎖，專案宣告完工。
> 任一列「待修」請於 issue #36 留言列出落差項目並回退至對應前序 story（5.x / 7.x / 8.x / 9.x / 10.x / 11.2）。

## 6. Artefacts 清單

本次 commit 預先建立目錄佔位（`.gitkeep`）：

- `docs/qa/artefacts/story-12.2/.gitkeep`
- `docs/qa/artefacts/story-12.2/side_by_side/.gitkeep`

PO 簽核前需補（由 §2 / §3 落地）：

- `app_home.png` / `app_playback.png` / `app_alerts.png` / `app_settings.png`
- `app_desktop_home.png`
- `state_s01_*.png` … `state_s07_*.png`
- `side_by_side/*.png`（每張對應上述截圖 + design 基準的左右合成）
- 本檔 §5 簽名欄位

## 7. 盤點 metadata

- 盤點作者：autonomous implementation engineer (Claude)
- 盤點日期：2026-05-18
- 依據檔案：
  - `.design-source/html/index.html` @ commit `38f334d`
  - `backend/design_tokens/screens/*.png`（透過 `.design-source/screens/` 鏡像）
- 狀態：**待 [HUMAN] 截圖、合成、簽名**。本 commit 僅落地 worksheet 與 artefacts 目錄佔位；最終截圖、勾選、簽名須由 PO 在 PR 上完成。
