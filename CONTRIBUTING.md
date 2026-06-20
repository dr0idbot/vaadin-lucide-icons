# Contributing

## Development Setup

- Java 21+
- Maven 3.9+

## Building

```shell
mvn -pl lucide-icons install
```

## Running Tests

```shell
mvn -pl lucide-icons test
```

## Running the Demo

```shell
mvn -pl lucide-icons-demo spring-boot:run
```

Then open http://localhost:8080.

## Updating Icons

To sync with the latest Lucide release:

```shell
mvn -pl lucide-icons-generator -q compile exec:java
```

This downloads fresh SVG files and regenerates `LucideIcon.java`.

## Code Style

- No Javadoc comments in source code
- Follow existing patterns in the codebase
- Keep the public API minimal

## Pull Requests

1. Ensure all tests pass
2. Update the CHANGELOG if adding features
3. Keep changes focused on a single concern
