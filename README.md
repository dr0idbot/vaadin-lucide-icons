# Vaadin Lucide Icons

[![CI Build](https://github.com/droidbot/vaadin-lucide-icons/actions/workflows/ci-build.yml/badge.svg)](https://github.com/droidbot/vaadin-lucide-icons/actions/workflows/ci-build.yml)

Server-side Lucide icon integration for Vaadin 24+.

This add-on provides access to the entire [Lucide](https://lucide.dev) icon set (1700+ icons) as native Vaadin components
backed by the `<vaadin-icon>` web component — the same infrastructure used by `VaadinIcon`.

## Installation

### Maven

```xml
<dependency>
    <groupId>io.droidbot</groupId>
    <artifactId>v-lucide-icons</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.droidbot:v-lucide-icons:1.0.0'
```

## Usage

### Basic

```java
Button saveButton = new Button("Save");
saveButton.setIcon(LucideIcon.SAVE.create());
```

### Customization

```java
var icon = LucideIcon.STAR.create();
icon.setColor("#ff6b00");
icon.setSize("48px");
icon.setStrokeWidth(1.5);
button.setIcon(icon);
```

### Accessibility

```java
// Decorative icon (hidden from screen readers)
var icon = LucideIcon.SAVE.create();
icon.setDecorative(true);

// Labelled icon
icon.getElement().setAttribute("aria-label", "Save the document");
```

### Predefined sizes

```java
icon.setSize(LucideSvgIcon.IconSize.M);  // 24px
icon.setSize(LucideSvgIcon.IconSize.XL); // 48px
```

## API

| Method | Source | Description |
|--------|--------|-------------|
| `setColor(String)` | `SvgIcon` | Sets icon color via CSS `fill` (e.g. `"red"`, `"var(--lumo-primary-text-color)"`) |
| `setSize(String)` | `AbstractIcon` | Sets both width and height (e.g. `"24px"`, `"2em"`) |
| `setSize(IconSize)` | `LucideSvgIcon` | Sets size from predefined constant (`S`/`M`/`L`/`XL`/`XXL`) |
| `setStrokeWidth(double)` | `LucideSvgIcon` | Sets SVG stroke width using `--vaadin-icon-stroke-width` CSS variable |
| `setDecorative(boolean)` | `LucideSvgIcon` | Marks icon as presentational (hidden from assistive tech) |

## Public API

The public API consists of exactly three types:

- **`LucideIcon`** — enum of all 1700+ icons
- **`LucideSvgIcon`** — extends Vaadin's `SvgIcon` (native `<vaadin-icon>` web component)
- **`LucideIconFactory`** — creates and validates icon instances

## Architecture

Unlike a generic span-based SVG renderer, this add-on integrates directly with Vaadin's icon infrastructure:

- **`LucideSvgIcon` extends `SvgIcon`** — which extends `AbstractIcon<@Tag("vaadin-icon")>`, the same base class used by `Icon` for `VaadinIcon`. This means Lucide icons use the exact same rendering pipeline as native Vaadin icons.
- **SVGs are loaded via the `src` attribute** — the `<vaadin-icon>` web component fetches SVGs as static resources. No inline SVG injection, no innerHTML manipulation.
- **Stroke width uses Vaadin's built-in CSS variable** — `--vaadin-icon-stroke-width` is natively supported by the `vaadin-icon` shadow DOM via `@container style()` queries.
- **SVGs are normalized at generation time** — `width`/`height` attributes are stripped (sizing is controlled by the web component), XML declarations and comments are removed.
- **Lazy loading by default** — SVGs are fetched by the browser only when the icon is rendered. Zero startup overhead.

### Why this approach?

| Concern | Span-based (old) | `SvgIcon`-based (new) |
|---------|-----------------|----------------------|
| Rendering | `@Tag("span")` + `innerHTML` | Native `<vaadin-icon>` web component |
| Theming | Manual CSS | `--vaadin-icon-color`, `--vaadin-icon-stroke-width` |
| Color | `color` CSS property | `fill` CSS property (Vaadin standard) |
| Accessibility | Manual | `HasAriaLabel`, `setDecorative()` |
| Performance | SVG content in every component | Browser-cached SVG fetch |
| Vaadin integration | Custom component | Same base as `VaadinIcon` |

## Requirements

- Java 21+
- Vaadin 24+ (built with Vaadin 25)
- Jakarta EE 11 (if using servlet environment)

## Release Process

1. Update icons (optional):
   ```shell
   mvn -pl v-lucide-icons-generator -q compile exec:java
   ```

2. Prepare release:
   ```shell
   mvn release:prepare
   ```

3. Push the tag — the [release workflow](.github/workflows/release-build.yml) publishes to Maven Central automatically.

## Project Structure

```
vaadin-lucide-icons/
├── v-lucide-icons/                 # Main add-on module
│   ├── src/main/java/            # LucideIcon enum, LucideSvgIcon, LucideIconFactory
│   ├── src/main/resources/       # SVG files under META-INF/resources/lucide/
│   └── src/test/java/            # Unit tests (28+ tests)
├── v-lucide-icons-demo/            # Spring Boot demo application
├── v-lucide-icons-generator/       # Icon download and code generation tool
└── pom.xml                       # Parent POM
```

## Licensing

- This add-on is licensed under **Apache 2.0**.
- Lucide icons are licensed under **ISC License** — see [`LICENSE`](v-lucide-icons/src/main/resources/META-INF/resources/lucide/LICENSE).
- Lucide is a community-maintained fork of [Feather Icons](https://feathericons.com/).
