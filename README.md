# Vaadin Lucide Icons

[![CI Build](https://github.com/droidbot-ind/vaadin-lucide-icons/actions/workflows/ci-build.yml/badge.svg)](https://github.com/droidbot-ind/vaadin-lucide-icons/actions/workflows/ci-build.yml)

Server-side [Lucide](https://lucide.dev) icon integration for Vaadin 24+ (Java 25).

## Tech Stack

- **Vaadin 25** — `<vaadin-icon>` web component via `SvgIcon`
- **Java 25** — Multi-module Maven project
- **Lucide** — 1986 open-source icons (ISC license)

## Run After Cloning

```bash
# Build the icon library (no demo)
mvn -pl v-lucide-icons install

# Run the demo app
mvn -pl v-lucide-icons-demo spring-boot:run
```

Then open `http://localhost:8080`.

## Usage

```xml
<dependency>
    <groupId>io.droidbot</groupId>
    <artifactId>v-lucide-icons</artifactId>
    <version>1.0.0</version>
</dependency>
```

```java
// Create an icon
LucideIcon.SAVE.create();

// Customize
var icon = LucideIcon.STAR.create();
icon.setColor("#ff6b00");
icon.setSize("48px");
icon.setStrokeWidth(1.5);
button.setIcon(icon);

// Accessibility
icon.setDecorative(true);                           // hide from screen readers
icon.getElement().setAttribute("aria-label", "Star"); // labelled
```

### API

| Method | Source | Description |
|--------|--------|-------------|
| `setColor(String)` | `SvgIcon` | Icon color via CSS `color` |
| `setSize(String)` | `AbstractIcon` | Width and height (e.g. `"24px"`, `"2em"`) |
| `setStrokeWidth(double)` | `LucideSvgIcon` | SVG stroke width |
| `setDecorative(boolean)` | `LucideSvgIcon` | Mark as presentational |

### Public Types

- **`LucideIcon`** — enum of all 1986 icons
- **`LucideSvgIcon`** — extends Vaadin's `SvgIcon`
- **`LucideIconFactory`** — creates and validates icon instances
