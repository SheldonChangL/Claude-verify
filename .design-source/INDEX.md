# Design source mirror

Source URL: https://motocam.netlify.app
Mirrored: 2026-05-18T00:16:00Z
Routes captured: 1
Assets captured: 10
Screenshots: 0

## Routes

| Route | HTML | Screenshot |
|---|---|---|
| / | html/index.html | (unavailable) |

The source is a single-page static HTML mockup (no SPA routing, no `<a href>` navigation between pages). The entire UI — Home / Playback / Alerts / Settings screens plus the phone-frame chrome — lives inside `html/index.html` as separate `<section>`/`.screen` blocks toggled by the bottom tab bar in pure CSS/JS. There are no additional routes to mirror.

## Assets

| Folder | File | Source |
|---|---|---|
| assets/icons/ | favicon.svg | /assets/icons/favicon.svg |
| assets/icons/ | icon_home_active.svg | /assets/icons/icon_home_active.svg |
| assets/icons/ | icon_playback.svg | /assets/icons/icon_playback.svg |
| assets/icons/ | icon_alert.svg | /assets/icons/icon_alert.svg |
| assets/icons/ | icon_settings.svg | /assets/icons/icon_settings.svg |
| assets/icons/ | btn_shutter.svg | /assets/icons/btn_shutter.svg |
| assets/images/ | user-moto.png | /assets/images/user-moto.png |
| assets/images/ | object-car.png | /assets/images/object-car.png |
| assets/images/ | object-moto.png | /assets/images/object-moto.png |
| assets/css/ | noto-sans-tc.css | Google Fonts CSS (Noto Sans TC 400/500/700) |
| assets/fonts/ | noto-sans-tc.woff2 | fonts.gstatic.com — Noto Sans TC variable woff2 (the CSS references the same file URL for all three weights, deduped to one) |

The CSS `url(...)` references found inside the inline `<style>` block (`#skyG`, `#horizGlow`, `#roadG`, `#laneEdgeG`, `#vig`, `#laneStripG`) are SVG fragment identifiers — references to `<linearGradient>` / `<radialGradient>` definitions embedded inline in the same HTML file — not external assets, so nothing to download.

## Limitations

- **Screenshots unavailable: no rendering MCP configured.** The mirror sub-agent had no `preview_start` / `preview_screenshot` capability in this run, so `screens/` is empty. The static phone-frame mockup renders in any modern browser; if pixel references are needed, open `html/index.html` directly or use the `.design-source/screens/*.png` files already checked in by the PRD authors.
- **HTML is the raw server response, not a post-JS-execution DOM snapshot.** That is fine here because the source is plain HTML+CSS (no SPA shell, no client-side router) — the file already contains every screen's markup verbatim. Implementation agents reading `html/index.html` get the same DOM a browser would render.

## How implementation agents use this

When porting a screen, search `html/index.html` for the screen's `<section class="screen ...">` block (Home / Playback / Alerts / Settings) to see exact DOM structure, class names, inline styles, and hard-coded text strings. Cross-reference against the PNG screenshots in the project's existing `design_tokens/screens/` directory for rendered appearance. SVG icons under `assets/icons/` are original files — convert directly to Android `VectorDrawable` rather than redrawing from a description. Color tokens, radii, and font stack are defined in the `:root { --bg, --brand, --t1, ... }` block near the top of the HTML.
