# Story 10.2 — Desktop Home Pixel Diff 簽核 Worksheet

Tracker: https://github.com/SheldonChangL/Claude-verify/issues/32
Requirement IDs: FR-6, NFR-2, NFR-10
Depends on: Story 10.1（Desktop Home 裁切策略註解，issue #31）

本檔案是 **[HUMAN] 視覺 gate** 簽核紀錄，需由 PO 親自完成截圖比對與簽名後合入。

---

## 1. 裁切策略回顧（Story 10.1 結論）

`desktop_home.png` 與 `home.png` 的差異僅在外層 device frame / 桌面背景，
裝置螢幕區內容（content viewport）與 mobile portrait 完全相同。

故 `DesktopHomeScreen()` 直接委派至 `HomeScreen()`，**不另作 landscape / wide variant**，
也不在 `MotoNavHost` 註冊獨立 route（避免重工與導覽圖膨脹）。

## 2. 環境前置

- AVD：Pixel 6, API 35（Android 15）
- App build：`ai-factory/github-32` HEAD（包含 Story 10.1 註解 + 本 story 的 Preview）
- 參考圖：`.design-source/screens/desktop_home.png`
  - ⚠ 若 `.design-source/screens/` 為空（本 repo 目前狀態），請改用
    `https://motocam.netlify.app` 桌機瀏覽器截圖，**並先以圖像工具裁去外層
    device frame**（依 §1 策略），存回該路徑後再續行。

## 3. 取得 App 截圖

`DesktopHomeScreen()` 等同於 `HomeScreen()`，可任選下列其一取得截圖：

```bash
# 1. 啟動 Pixel 6 API 35 emulator
# 2. 安裝最新 debug build
./gradlew :app:installDebug

# 3. 啟動 App、停留於 Home tab，畫面進入待機（idle，非 alert 狀態）

# 4. 截圖落地
mkdir -p docs/qa/artefacts/story-10.2
adb exec-out screencap -p > docs/qa/artefacts/story-10.2/app_desktop_home.png
```

或於 Android Studio 開啟 `DesktopHomeScreen.kt`，使用 `DesktopHomeScreenPreview`
Preview 直接匯出 390 × 844 dp PNG。

## 4. Side-by-side 比對

將 `docs/qa/artefacts/story-10.2/app_desktop_home.png` 與
（裁切後的）`.design-source/screens/desktop_home.png` 並排輸出：

```
docs/qa/artefacts/story-10.2/side_by_side.png
```

附於 PR 描述與 issue #32 收尾留言。

## 5. 驗收檢查表

- [ ] 裁切後 reference 與 `home.png` 內容一致（佐證 10.1 結論成立）
- [ ] App 截圖與裁切後 reference pixel diff ≤ 2px
- [ ] `DesktopHomeScreen()` 未在 `MotoNavHost` 另開 route（檢 `MotoNavHost.kt`）
- [ ] 未引入任何 desktop landscape / wide variant composable

## 6. [HUMAN] 簽核

| 項目 | 結果 | 簽名 | 日期 |
|---|---|---|---|
| 裁切策略落地正確（無獨立 route / 無 wide variant） | ☐ 通過 / ☐ 待修 |  |  |
| Diff ≤ 2px、FR-6 視覺覆蓋收斂 | ☐ 通過 / ☐ 待修 |  |  |

> 兩列皆「通過」後，Story 10.2 視為 Done，FR-6 desktop home 視覺覆蓋完成。

## 7. Artefacts 清單（PR 必附）

- `docs/qa/artefacts/story-10.2/app_desktop_home.png`
- `docs/qa/artefacts/story-10.2/side_by_side.png`
- 本檔（含 §6 簽名）
