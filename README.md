# Nosh India (Android)

Native Kotlin + Jetpack Compose app for Nosh India — homemade Indian
mithai (sweets), delivered fresh. Built from the live site at
https://hcmtech-official.github.io/hcm-tech/noshindia/

## Scope

Full site, as agreed: Home (heritage story, delivery info), Menu,
an in-app Order form (product + quantity/weight tiers, live total,
contact details), and Reviews. No backend, no payment processing —
placing an order builds a WhatsApp message and hands it to the
WhatsApp app, matching how the live site actually fulfills orders
(coordinated manually via WhatsApp/phone).

## Before you ship this for real

**The WhatsApp number is a placeholder.** In `MainActivity.kt`:

```kotlin
private val whatsAppNumber = "REPLACE_WITH_BUSINESS_NUMBER"
```

Replace this with the real Nosh India WhatsApp business number in
international format, digits only, no "+" or leading zero — e.g. an
Australian mobile `04XX XXX XXX` becomes `"614XXXXXXXX"`. Until this
is set, tapping "Place Order" will open WhatsApp to an invalid chat.

## Stack

- Jetpack Compose + Material 3
- Navigation-Compose, 4-tab bottom nav: Home / Menu / Order / Reviews
- No local database — menu/testimonial content is static Kotlin data
  in `data/`; order state lives in `OrderViewModel` for the current
  session only (nothing is persisted, since an order is meant to be
  sent immediately via WhatsApp, not saved)
- Pricing tiers (10/20/30/50/100 pieces, 0.5/1/2/5 kg) and unit prices
  match the live site exactly

## How to build

Open this folder in Android Studio, let it sync, run on device — or
use the GitHub Actions workflow in `.github/workflows/build-apk.yml`
to get a compiled APK without a computer (push to a GitHub repo,
download the `nosh-india-debug-apk` artifact from the Actions run).

## Project layout

```
app/src/main/java/com/noshindia/app/
  data/           Static product, pricing tier, and testimonial data
  ui/theme/       Heritage palette (cream/cocoa/maroon/saffron), type, Material3 theme
  ui/home/        Hero, feature grid, heritage story, delivery info
  ui/menu/        Product cards with pricing
  ui/order/       Order form: product + tier dropdowns, live total, WhatsApp handoff
  ui/reviews/     Testimonial cards
  ui/components/  Shared SectionHeading, Chip
  ui/navigation/  Bottom nav graph
  MainActivity.kt WhatsApp intent handling
```

## Brand fonts

Falls back to system serif/sans-serif (`ui/theme/Type.kt`) — same
reason as prior builds: no network access to Google Fonts in the
build sandbox. Drop real `.ttf` files into `app/src/main/res/font/`
and swap the `FontFamily.Serif` / `FontFamily.SansSerif` references
to use real display/body faces.
