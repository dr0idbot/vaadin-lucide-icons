# Changelog

## [1.0.0] - 2026-06-19

### Added
- Initial release with 1737 Lucide icons
- `LucideIcon` enum with generated constants for all icons
- `LucideSvgIcon` extending Vaadin's native `SvgIcon` (backed by `<vaadin-icon>` web component)
- `LucideIconFactory` with in-memory SVG content caching
- `lucide-icons-generator` module for downloading icons and regenerating the enum
- Icon normalization at generation time (strips XML headers, `width`/`height`, comments)
- `lucide-icons-demo` Spring Boot demo application
- Accessibility support via `setDecorative(boolean)` and ARIA attributes
- Predefined `IconSize` enum (`S`/`M`/`L`/`XL`/`XXL`)
- Unit tests (28 tests covering icon loading, caching, component customization, accessibility, sizing, and SVG source verification)
- GitHub Actions for CI and Maven Central publishing

### Architecture
- **Native Vaadin integration**: `LucideSvgIcon` extends `SvgIcon` which extends `AbstractIcon<@Tag("vaadin-icon")>` — the same base class as `Icon` for `VaadinIcon`. Icons render through the native `<vaadin-icon>` web component.
- **URL-based SVG loading**: SVGs are served as static classpath resources and loaded by the browser via the `src` attribute. No inline SVG injection.
- **Stroke width via CSS variable**: Uses `--vaadin-icon-stroke-width` — a CSS custom property natively supported by the `vaadin-icon` shadow DOM.
- **Lazy loading**: SVGs are fetched by the browser only when rendered. Zero startup overhead with HTTP caching.
