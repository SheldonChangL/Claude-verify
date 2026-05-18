# Story 9.4 — Settings Pixel Diff 簽核 Worksheet

Tracker: https://github.com/SheldonChangL/Claude-verify/issues/30
Requirement IDs: OPS-3, OPS-5
Depends on: Story 9.3（SettingsScreen 1:1 mirror）

本檔案是 **[HUMAN] 視覺 gate** 簽核紀錄，需由 PO 親自完成截圖比對與簽名後合入。

---

## 1. 環境前置

- AVD：Pixel 6, API 35（Android 15）
- App build：`ai-factory/github-30` HEAD（已包含 Story 9.3 SettingsScreen mirror、Story 9.2 SettingsToggle composable、Story 9.1 Settings icon 匯入）
- 參考圖：本次自動產出版本已落地至
  `docs/qa/artefacts/story-9.4/design_settings.png`
  （來源：`https://motocam.netlify.app/settings`，Chrome headless 800x1700 抓取）。
  - `.design-source/screens/settings.png` 在本 milestone 為空（mirror 階段未抓到），
    故改以 deployed URL 為設計基準；若日後 mirror 補回 `settings.png`，可覆蓋此檔。

## 2. 取得 App 截圖

```bash
# 1. 啟動 Pixel 6 API 35 emulator
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 啟動 App、切換至 Settings tab（底部第 4 顆 icon，gear），等待 settings 列表完成渲染
#    並向下捲動截一張 full-scroll 圖以涵蓋警示功能 / BSD / RCW / 事件錄影 / 儲存與錄影 / 韌體與系統 / 版本資訊

# 4. 截圖落地至本檔同層 artefacts/
mkdir -p docs/qa/artefacts/story-9.4
adb exec-out screencap -p > docs/qa/artefacts/story-9.4/app_settings.png
```

> 若使用實機，請開啟「USB 偵錯」並確認 `adb devices` 可見裝置。

## 3. Side-by-side 比對

本 PR 已附自動生成版本：

- `docs/qa/artefacts/story-9.4/side_by_side.png`
  （左：design = `design_settings.png`；右：app 佔位框，待 §2 截圖補上）

PO 流程：
1. 依 §2 取得 `app_settings.png` 後，用任一影像工具
   （Preview / Figma / Photoshop / `compare` from ImageMagick）
   重新合成左右並排圖，**覆蓋** `side_by_side.png`。
2. 於 PR 描述與 issue #30 收尾留言附上最終版 `side_by_side.png`。

## 4. 驗收檢查表

每項勾選並於右側註記實測值。

- [ ] 頁面標題「設定」左對齊、字級 h1、色 `#FFFFFF`、bold
- [ ] 群組標籤「裝置」字色 `#AAAAAA`、字級 small，貼齊卡片上緣
- [ ] 裝置卡：圓角 14dp、surface `#1A1F2E`、左側 rounded camera icon tile、名稱「MotoCam KC003」、副標「韌體版本 v2.1.4」、狀態列「● 已連線」綠點 `#30D158`
- [ ] 儲存卡：「儲存空間」label + 「10 GB」value + 進度條 fill `#AACC03`、圖例「● 已使用 4.2 GB · 剩餘 5.8 GB」
- [ ] 群組「即時畫面」：顯示模式 segmented 2-option（即時影像 selected solid `#AACC03` + 黑字）、省電模式進入時間 4-option（3 分 selected）
- [ ] 群組「警示功能」：BSD/RCW toggle row、靈敏度 segmented 顏色與字級一致
- [ ] 群組「儲存與錄影」：循環錄影容量上限 segmented 行為一致
- [ ] 群組「韌體與系統 / 版本資訊」：欄位順序與字級一致
- [ ] hairline border `rgba(255,255,255,.08)`、卡片內 padding 16dp、列間距 12dp
- [ ] 底部 tab：Settings icon active（`#AACC03` + glow），其餘 stroke 灰
- [ ] **禁止項目未出現**：頂端帳號列、登出 / 刪除帳號、主題切換、語言切換（皆為 forbidden additions）
- [ ] 整體 pixel diff ≤ 2px（人眼判斷或 ImageMagick `compare -metric AE` 量測）

## 5. [HUMAN] 簽核

| 項目 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| 裝置 / 儲存 / segmented / toggle 視覺一致 | ☐ 通過 / ☐ 待修 |  |  |
| Diff ≤ 2px、Settings 視覺 gate 收斂 | ☐ 通過 / ☐ 待修 |  |  |

> 兩列皆「通過」後，Story 9.4 視為 Done，Settings Screen group 視覺 gate 解鎖；
> 任一列「待修」請於 issue #30 留言列出落差項目並回退至 Story 9.3 修正。

## 6. Artefacts 清單

本次 commit 已附（自動生成）：

- `docs/qa/artefacts/story-9.4/design_settings.png` — design 基準（deployed URL）
- `docs/qa/artefacts/story-9.4/app_settings_placeholder.png` — app 欄位佔位框
- `docs/qa/artefacts/story-9.4/side_by_side.png` — 起始版 side-by-side（右半待 PO 覆蓋）

PO 簽核前需補：

- `docs/qa/artefacts/story-9.4/app_settings.png` — 由 §2 emulator/adb 取得
- 重新合成後覆蓋 `side_by_side.png`
- 本檔（含 §5 簽名）
