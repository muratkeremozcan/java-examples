# Repository Guidelines

## Project Structure & Module Organization

- `src/<nn_topic>/Example.java` holds standalone lessons; keep numbering consistent and prefer PascalCase class names.
- `config/pmd/pmd-ruleset.xml` refines PMD checks; extend here rather than in build scripts.
- `scripts/setup-hooks.sh` installs repo Git hooks; re-run when hooks change.
- `build.gradle` and `Makefile` centralize build logic; generated artifacts land under `build/`.

## Build, Test, and Development Commands

- `make dev` boots the Spring app with hot reload; stop via `make stop`.
- `make build` (`./gradlew build -x test`) compiles lessons and quality gates.
- `make test` runs `./gradlew test`; add tests under `src/test/java`.
- `make lint` wraps Checkstyle, PMD, and SpotBugs; use `make fix` for auto-format + lint.
- Use `./gradlew listSimple` to discover runnable snippets and `./gradlew runSimple -Pexample=01_intro/WelcomeToJava` to execute one quickly.

## Coding Style & Naming Conventions

- Java 21 toolchain with Google Java Format enforced via `make format` / `./gradlew format`.
- Follow Google Java Style defaults: 2-space continuations, 100-column limit, PascalCase classes, lowerCamelCase methods/fields.
- Keep lesson folders numbered (`01_intro`) and class names descriptive; avoid package declarations unless refactoring into modules.
- SpotBugs and PMD are blocking; treat warnings as must-fix before merging.

## Testing Guidelines

- Use JUnit 5 (Gradle defaults) for new tests; name files `<ClassName>Test` or `<Feature>IT`.
- Place unit tests under `src/test/java/<lesson>` mirroring the lesson folder.
- Run `make test` locally; generate coverage with `./gradlew jacocoTestReport` when touching critical flows.
- Failing or flaky tests must be resolved before opening a PR.

## Commit & Pull Request Guidelines

- Prefer Conventional Commits (`feat:`, `fix:`, `docs:`); examples in history include `feat: interfaces 1`.
- Squash exploratory commits before review; each commit should compile and pass lint.
- Run `make precommit` (format, lint, SpotBugs, build) before pushing.
- PRs need a brief summary, linked issue (if any), test evidence or command output, and screenshots when UI-affecting.
