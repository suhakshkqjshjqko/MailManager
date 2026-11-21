# Mail Manager (Android)

A minimal, modular Android application built with Kotlin, Material 3, and clean MVVM architecture.

## Project Structure

- `core/feature/` — `AppFeature` contract & `FeatureRegistry` for plugging in independent future features without modifying existing modules.
- `core/utils/` — Shared utilities (`ClipboardHelper`).
- `features/mailmanager/` — Self-contained Mail Manager feature:
  - `data/` — `LocalMailStorage` (SharedPreferences) & `MailRepositoryImpl`
  - `domain/` — `MailRepository` interface
  - `ui/` — `MailHomeFragment`, `MailEditorFragment`, and `MailViewModel`

## Building an Installable APK

1. **In Android Studio**: Open this folder, let Gradle sync, and select **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
2. **Without a PC (GitHub Actions)**: Push this project to a GitHub repository, open the **Actions** tab, and download `MailManager-debug-apk` from the workflow run.
