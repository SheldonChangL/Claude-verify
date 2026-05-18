# Story 7.4 — Playback Pixel Diff 簽核 Worksheet

Tracker: https://github.com/SheldonChangL/Claude-verify/issues/22
Requirement IDs: OPS-3, OPS-5
Depends on: Story 7.3（PlaybackScreen 1:1 mirror）

本檔案是 **[HUMAN] 視覺 gate** 簽核紀錄，需由 PO 親自完成截圖比對與簽名後合入。

---

## 1. 環境前置

- AVD：Pixel 6, API 35（Android 15）
- App build：`ai-factory/github-22` HEAD（已包含 Story 7.3 PlaybackScreen mirror、Story 7.2 VideoRow composable、Story 7.1 Playback icon 匯入）
- 參考圖：`.design-source/screens/playback.png`（或 `playback_full.png` 取長截圖）
  - ⚠ 若 `.design-source/screens/` 為空，請改用 `https://motocam.netlify.app/playback` 桌機瀏覽器截圖，或從 PRD 附件取得 `playback.png` 後置入該資料夾再續行。

## 2. 取得 App 截圖

```bash
# 1. 啟動 Pixel 6 API 35 emulator
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 啟動 App、切換至 Playback tab（底部第 3 顆 icon），等待 video list 完成渲染

# 4. 截圖落地至本檔同層 artefacts/
mkdir -p docs/qa/artefacts/story-7.4
adb exec-out screencap -p > docs/qa/artefacts/story-7.4/app_playback.png
```

> 若使用實機，請開啟「USB 偵錯」並確認 `adb devices` 可見裝置。

## 3. Side-by-side 比對

請使用任一影像工具（Preview / Figma / Photoshop / `compare` from ImageMagick）將
`docs/qa/artefacts/story-7.4/app_playback.png` 與 `.design-source/screens/playback.png`
左右並排，輸出比對圖：

```
docs/qa/artefacts/story-7.4/side_by_side.png
```

附於 PR 描述與 issue #22 收尾留言。

## 4. 驗收檢查表

每項勾選並於右側註記實測值。

- [ ] 頁面標題「回放」置中、字級 h1、色 `#FFFFFF`
- [ ] Filter chip row：日期 chip（calendar + chevron）／「全部 12」selected（solid `#AACC03` + 黑字）／「BSD …」outlined／「選取」trailing outlined
- [ ] 區段標題「今天 · 2026-05-14」字色 `#AAAAAA`、字級 small
- [ ] VideoRow card：圓角 14dp、surface `#1A1F2E`、hairline border `rgba(255,255,255,.08)`、padding 16dp、列間距 12dp
- [ ] Thumbnail：16:9、圓角 8dp、play-triangle overlay、右下時長 pill「00:18」
- [ ] 檔名 bold（如 `20260514_2146_BSD.ts`）、type chip（BSD / 截圖 / RCW）pill 顏色一致、時間 `9:46 PM`、footer `12.4 MB · 00:18` muted
- [ ] 底部 tab：Playback icon active（`#AACC03` + glow），其餘 stroke 灰
- [ ] 整體 pixel diff ≤ 2px（人眼判斷或 ImageMagick `compare -metric AE` 量測）

## 5. [HUMAN] 簽核

| 項目 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| VideoRow / chip / thumbnail 視覺一致 | ☐ 通過 / ☐ 待修 |  |  |
| Diff ≤ 2px、Playback 視覺 gate 收斂 | ☐ 通過 / ☐ 待修 |  |  |

> 兩列皆「通過」後，Story 7.4 視為 Done，Playback Screen group 視覺 gate 解鎖；
> 任一列「待修」請於 issue #22 留言列出落差項目並回退至 Story 7.3 修正。

## 6. Artefacts 清單（PR 必附）

- `docs/qa/artefacts/story-7.4/app_playback.png`
- `docs/qa/artefacts/story-7.4/side_by_side.png`
- 本檔（含 §5 簽名）
