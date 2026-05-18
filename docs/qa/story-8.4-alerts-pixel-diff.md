# Story 8.4 — Alerts Pixel Diff 簽核 Worksheet

Tracker: https://github.com/SheldonChangL/Claude-verify/issues/26
Requirement IDs: OPS-3, OPS-5
Depends on: Story 8.3（AlertsScreen 1:1 mirror）

本檔案是 **[HUMAN] 視覺 gate** 簽核紀錄，需由 PO 親自完成截圖比對與簽名後合入。

---

## 1. 環境前置

- AVD：Pixel 6, API 35（Android 15）
- App build：`ai-factory/github-26` HEAD（已包含 Story 8.3 AlertsScreen mirror、Story 8.2 AlertRow composable、Story 8.1 Alerts icon 匯入）
- 參考圖：本次自動產出版本已落地至
  `docs/qa/artefacts/story-8.4/design_alerts.png`
  （來源：`https://motocam.netlify.app/alerts`，Chrome headless 800x1700 抓取）。
  - `.design-source/screens/alerts.png` 在本 milestone 為空（mirror 階段未抓到），
    故改以 deployed URL 為設計基準；若日後 mirror 補回 `alerts.png`，可覆蓋此檔。

## 2. 取得 App 截圖

```bash
# 1. 啟動 Pixel 6 API 35 emulator
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 啟動 App、切換至 Alerts tab（底部第 2 顆 icon，bell with red dot），等待 alert list 完成渲染

# 4. 截圖落地至本檔同層 artefacts/
mkdir -p docs/qa/artefacts/story-8.4
adb exec-out screencap -p > docs/qa/artefacts/story-8.4/app_alerts.png
```

> 若使用實機，請開啟「USB 偵錯」並確認 `adb devices` 可見裝置。

## 3. Side-by-side 比對

本 PR 已附自動生成版本：

- `docs/qa/artefacts/story-8.4/side_by_side.png`
  （左：design = `design_alerts.png`；右：app 佔位框，待 §2 截圖補上）

PO 流程：
1. 依 §2 取得 `app_alerts.png` 後，用任一影像工具
   （Preview / Figma / Photoshop / `compare` from ImageMagick）
   重新合成左右並排圖，**覆蓋** `side_by_side.png`。
2. 於 PR 描述與 issue #26 收尾留言附上最終版 `side_by_side.png`。

## 4. 驗收檢查表

每項勾選並於右側註記實測值。

- [ ] 頁面標題「觸發事件」置中、字級 h1、色 `#FFFFFF`
- [ ] Filter chip row：日期 chip（calendar + chevron）／「全部 10」selected（solid `#AACC03` + 黑字）／「BSD …」outlined
- [ ] 區段標題「今天 · 2026-05-14」字色 `#AAAAAA`、字級 small
- [ ] AlertRow card：圓角 14dp、surface `#1A1F2E`、hairline border `rgba(255,255,255,.08)`、padding 16dp、列間距 12dp
- [ ] 左側圓形 icon（⓵ / ⚠）、中文標題（左側盲點警示 / 後方防撞警示 / 右側盲點警示 / 車輛傾倒偵測）
- [ ] 時間「9:46 PM」muted、type chip（BSD outline `#FF9F0A` / RCW outline `#FF4444` / 傾倒 pinker red `#FF5864`）pill 顏色一致
- [ ] 右側圓形「jump to playback」icon button
- [ ] 底部 tab：Alerts icon active（`#AACC03` + glow），其餘 stroke 灰
- [ ] 整體 pixel diff ≤ 2px（人眼判斷或 ImageMagick `compare -metric AE` 量測）

## 5. [HUMAN] 簽核

| 項目 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| AlertRow / chip / icon button 視覺一致 | ☐ 通過 / ☐ 待修 |  |  |
| Diff ≤ 2px、Alerts 視覺 gate 收斂 | ☐ 通過 / ☐ 待修 |  |  |

> 兩列皆「通過」後，Story 8.4 視為 Done，Alerts Screen group 視覺 gate 解鎖；
> 任一列「待修」請於 issue #26 留言列出落差項目並回退至 Story 8.3 修正。

## 6. Artefacts 清單

本次 commit 已附（自動生成）：

- `docs/qa/artefacts/story-8.4/design_alerts.png` — design 基準（deployed URL）
- `docs/qa/artefacts/story-8.4/app_alerts_placeholder.png` — app 欄位佔位框
- `docs/qa/artefacts/story-8.4/side_by_side.png` — 起始版 side-by-side（右半待 PO 覆蓋）

PO 簽核前需補：

- `docs/qa/artefacts/story-8.4/app_alerts.png` — 由 §2 emulator/adb 取得
- 重新合成後覆蓋 `side_by_side.png`
- 本檔（含 §5 簽名）
