# Story 12.1 — 全 screen Interactive State 校齊 Worksheet

> Issue: https://github.com/SheldonChangL/Claude-verify/issues/35
> Requirement IDs: FR-8, NFR-10
> Depends on: Story 7.4（Playback pixel-diff）、Story 8.4（Alerts pixel-diff）、Story 9.4（Settings pixel-diff）

本檔案為 **[HUMAN] 視覺 gate**：4 screens × 三狀態（pressed / focused / selected）截圖對照需由 PO 在 emulator 上親自完成並貼回本檔，才視為通過 FR-8。

---

## 1. 設計來源狀態盤點

`.design-source/html/index.html` 為單頁 SPA（mirror 唯一 HTML，以 CSS class 切換 liveview / playback / alerts / settings 視圖）。逐節 grep `:hover` / `:active` / `:focus` 結果：

| Selector | 觸發 | CSS effect | mirror 行號 |
|---|---|---|---|
| `.ni:active .ni-icon` | nav item icon press | `transform: scale(.85)` | 64 |
| `.fs-btn:active` | 全螢幕按鈕 press | `transform: scale(.92); background: rgba(0,0,0,.78)` | 97 |
| `.fall-btn:active` | 傾倒 overlay 按鈕 press | `transform: scale(.95)` | 244 |
| `.shutter-btn:active` | playback shutter press | `transform: scale(.9)` | 276 |
| `.lc-btn:active .lc-icon` | live controls icon press | `transform: scale(.9)` | 289 |
| `.lc-btn:active .rec-btn-sm` | live REC small button press | `transform: scale(.95)` | 296 |
| `.iq-panel-header:active` | 影像品質面板 header press | `background: rgba(255,255,255,.03)` | 315 |
| `.iq-reset-btn:active` | 影像品質 reset press | `background: rgba(255,255,255,.1)` | 345 |
| `.dbtn-bsd:active` / `.dbtn-rcw:active` / `.dbtn-tilt:active` | demo trigger（forbidden — 已 per Story 11.1 略過） | bg tint | 353/355/358 |

**結論**：
- mirror **完全沒有** `:hover` 與 `:focus` 規則（純 mobile-touch 設計，無 keyboard / D-pad navigation 路徑）。
- mirror 的「press 表達」一致採 `scale-down + 微量背景 tint`；無 ripple、無 outline、無 selected 邊框。
- 「selected」語意僅出現在底部 nav（`.ni.active`，行 50–55：以 icon / label 改色為主，不畫底線或 pill 背景）。

## 2. 與 App 實作對照

| Screen | Element | mirror state | App 實作 | 是否需調整 |
|---|---|---|---|---|
| Home | `BottomNavBar` item | `.ni:active` icon scale .85；`.ni.active` accent 染色 | `NavigationBarItem` selected/unselected 各自染色（accent / muted），press 走 Material ripple（已染 `MotoColors.Accent`） | 已對齊 |
| Home | 傾倒 overlay 按鈕 | `.fall-btn:active` scale .95 + REC pulse | `HomeScreen` fall overlay 按鈕沿用 `clickable` + ripple | 已對齊（ripple ≈ press tint） |
| Home | live REC / shutter / lc-btn | scale .9 / .95 | 共用 `clickable` + Material ripple | 已對齊 |
| Alerts | `AlertRow` 列項 | mirror 列項無 :active rule，僅靠 ripple-like 視覺 | `AlertRow` 整列 `clickable`（行 73、155） | 已對齊（採預設 ripple）|
| Playback | `VideoRow` 列項 | 同上 | `VideoRow` 整列 `clickable`（行 60） | 已對齊 |
| Playback | shutter / 控制鈕 | `.shutter-btn:active` scale .9 | `PlaybackScreen` 控制鈕 ripple | 已對齊 |
| Settings | `SettingsToggle` 列項 | mirror 設定列無 :active rule | `SettingsToggle.clickable`（行 34） | 已對齊 |
| Settings | tab 切換（事件 / 系統 / 影像）| 無 mirror 直接對應；mirror 之 settings 為單頁列表 | `SettingsScreen` 行 494 `clickable`（segmented selector） | 已對齊（selected = accent text，符合 nav 慣例） |
| 全域 | Focus ring | mirror 無 `:focus` | Compose 預設 focused indication（鍵盤 / D-pad 才會出現）走 `LocalIndication`；與 ripple 同色，符合 token | 已對齊（mirror 無對應規則可比對；App 僅在外接鍵盤情境出現 focus，視覺以 Accent 為主） |

> **PR 結論**：mirror 的互動狀態語意已被 Story 5.x / 7.x / 8.x / 9.x 過程中以 Compose 預設 `clickable` + `LocalRippleConfiguration(MotoColors.Accent)`（見 `MotoTheme.kt`）覆蓋。NavigationBar 的 selected 樣式於 `BottomNavBar.kt` 顯式指派 `MotoColors.Accent`。**本 story 不需新增 Modifier.indication / 自訂 InteractionSource**——任何進一步調整都會偏離 mirror 規格。

## 3. [HUMAN] 截圖採集步驟

```bash
# 1. 啟動 Pixel 6 API 35 emulator
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 依序切到 4 個 screen：home / alerts / playback / settings
#    每個 screen 操作三狀態並 adb 截圖
mkdir -p docs/qa/artefacts/story-12.1

# pressed：在可點擊元件上長按 ~1s，截圖時手指仍按住
adb exec-out screencap -p > docs/qa/artefacts/story-12.1/{screen}_pressed.png

# focused：以實體鍵盤（或 emulator 軟鍵盤 Tab 鍵）讓 focus 落在首個 focusable 元件
adb exec-out screencap -p > docs/qa/artefacts/story-12.1/{screen}_focused.png

# selected：對含 selected 語意的元件（BottomNavBar tab、Settings segmented selector）取 selected 狀態
adb exec-out screencap -p > docs/qa/artefacts/story-12.1/{screen}_selected.png
```

預期落地：

```
docs/qa/artefacts/story-12.1/
├── home_pressed.png        home_focused.png        home_selected.png
├── alerts_pressed.png      alerts_focused.png      alerts_selected.png
├── playback_pressed.png    playback_focused.png    playback_selected.png
└── settings_pressed.png    settings_focused.png    settings_selected.png
```

## 4. 對照 Checklist

逐 screen 簽核 — 若觀察到 App 與 mirror 不一致，於該行記錄差異並開 follow-up。

### 4.1 Home

- [ ] **pressed**：BottomNavBar icon 在按住時 scale ≈ .85（或對等 ripple 表達）；fall overlay 按鈕、live REC / shutter 在 press 期間有可察覺縮小或暗色 tint。
- [ ] **focused**：以 D-pad / 鍵盤 Tab 後焦點框沿 `MotoColors.Accent`；mirror 無對應規則，視為「最低限度可見」即可通過。
- [ ] **selected**：BottomNavBar 當前 tab icon / label 染 `#FFD133`；其他 tab 為 `MotoColors.Muted`。

### 4.2 Alerts

- [ ] **pressed**：`AlertRow` 整列 ripple 漫染 accent；mirror 無顯式 :active，僅需 ripple 在 token 內。
- [ ] **focused**：focus 落在 row 時 outline / ripple 可見；mirror 無對應。
- [ ] **selected**：mirror 與 App 皆無 selected 列表狀態 — 截圖以 BottomNavBar 「Alerts」 tab selected 為證。

### 4.3 Playback

- [ ] **pressed**：`VideoRow` press ripple；shutter / 控制鈕 press 時 scale-down 或 ripple 可見。
- [ ] **focused**：同 alerts。
- [ ] **selected**：BottomNavBar 「Playback」 tab selected。

### 4.4 Settings

- [ ] **pressed**：`SettingsToggle` 列 ripple；segmented selector tab 按下時短暫染色。
- [ ] **focused**：focus 落在 row 時 outline 可見。
- [ ] **selected**：segmented selector 當前段呈 `MotoColors.Accent` 文字 + accent underline；BottomNavBar 「Settings」 tab selected。

## 5. 不一致時處置

若 [HUMAN] 觀察到 App 與 mirror 差異：

1. **press 無視覺反饋** — 確認 `MotoTheme` 內 `LocalRippleConfiguration` 是否套用到該 composable 樹；必要時於該 composable 顯式套 `Modifier.clickable(interactionSource, indication = LocalIndication.current)`。
2. **selected 顏色錯誤** — 對齊到 `MotoColors.Accent`（`BottomNavBar.kt:44–48` 為樣板）。
3. **focused outline 完全不見** — 在該 composable 加 `Modifier.focusable()`；mirror 無對應規則，最低限度只要 focus traversal 不中斷即可。

凡需動原始碼，請另開 follow-up story（避免本 milestone 範圍蔓延）。

## 6. 簽核

- 盤點作者：autonomous implementation engineer (Claude)
- 盤點日期：2026-05-18
- 依據檔案：`.design-source/html/index.html` @ commit `9ea34a1`
- 狀態：**待 [HUMAN] 截圖 + 覆核**。本檔案為候選盤點與步驟說明；最終截圖、勾選、簽名須由 PO 在 PR 上完成。
