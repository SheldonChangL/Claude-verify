# Story 5.3 — Home Pixel Diff 簽核 Worksheet

Tracker: https://github.com/SheldonChangL/Claude-verify/issues/15
Requirement IDs: OPS-3, OPS-5, NFR-10
Depends on: Story 5.2（HomeScreen 1:1 mirror）

本檔案是 **[HUMAN] 視覺 gate** 簽核紀錄，需由 PO 親自完成截圖比對與簽名後合入。

---

## 1. 環境前置

- AVD：Pixel 6, API 35（Android 15）
- App build：`ai-factory/github-15` HEAD（已包含 Story 5.2 HomeScreen）
- 參考圖：`.design-source/screens/home.png`
  - ⚠ 若 `.design-source/screens/` 為空（本 repo 目前狀態），請改用 `https://motocam.netlify.app` 桌機瀏覽器截圖，或從 PRD 附件取得 `home.png` 後置入該資料夾再續行。

## 2. 取得 App 截圖

```bash
# 1. 啟動 Pixel 6 API 35 emulator（Android Studio Device Manager 或 avdmanager）
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 啟動 App、停留於 Home tab，畫面進入待機（idle，非 alert 狀態）

# 4. 截圖落地至本檔同層 artefacts/
mkdir -p docs/qa/artefacts/story-5.3
adb exec-out screencap -p > docs/qa/artefacts/story-5.3/app_home.png
```

> 若使用實機，請開啟「USB 偵錯」並確認 `adb devices` 可見裝置。

## 3. Side-by-side 比對

請使用任一影像工具（Preview / Figma / Photoshop / `compare` from ImageMagick）將
`docs/qa/artefacts/story-5.3/app_home.png` 與 `.design-source/screens/home.png`
左右並排，輸出比對圖：

```
docs/qa/artefacts/story-5.3/side_by_side.png
```

附於 PR 描述與 issue #15 收尾留言。

## 4. 驗收檢查表

每項勾選並於右側註記實測值。

- [ ] Hero 速度數字：字級 96sp、weight 900、色 `#AACC03`、有 lime glow
- [ ] 字型 fallback 中文字寬不破 diff（解除 PRD Open Question #2）
- [ ] BSD / RCW / 傾倒 / 清除 chip 顏色、外框、文字色一致
- [ ] Camera viewport 圓角 12dp、底部時間戳、右上「未錄影」pill
- [ ] 畫質調整 accordion、提醒 strip、shutter 圓鈕、chip 排列順序
- [ ] Bottom-tab：Home 圖示 active（`#AACC03` + glow），其餘 stroke 灰
- [ ] 整體 pixel diff ≤ 2px（人眼判斷或 ImageMagick `compare -metric AE` 量測）

## 5. [HUMAN] 簽核

| 項目 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| 中文字型 fallback 不破 diff | ☐ 通過 / ☐ 待修 |  |  |
| Diff ≤ 2px、M1 視覺 gate 收斂 | ☐ 通過 / ☐ 待修 |  |  |

> 兩列皆「通過」後，Story 5.3 視為 Done，M1 milestone 視覺 gate 解鎖；
> 任一列「待修」請於 issue #15 留言列出落差項目並回退至 Story 5.2 修正。

## 6. Artefacts 清單（PR 必附）

- `docs/qa/artefacts/story-5.3/app_home.png`
- `docs/qa/artefacts/story-5.3/side_by_side.png`
- 本檔（含 §5 簽名）
