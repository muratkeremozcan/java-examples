## Java Dev Environment

This repo captures a minimal, modern Java setup you can reuse for future projects.

- Java: 21 (LTS)
- Build: Gradle 8 (wrapper included)
- Framework: Spring Boot 3.x
- IDE: IntelliJ IDEA or VS Code (both supported)

### 1) One‑time setup on your machine

<details><summary>Show one‑time setup (Homebrew + JAVA_HOME)</summary>

```bash
brew install --cask temurin@21
brew install gradle maven
brew install --cask intellij-idea-ce  # or intellij-idea

# Code quality tools (optional - formatter is built into Gradle)
# brew install google-java-format  # Not needed - using built-in formatter

# Optional but useful
brew install --cask docker
brew install jenv
```

Ensure `JAVA_HOME` points to JDK 21 (add to `~/.zshrc`):

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

Verify:

```bash
java -version
gradle -v || true  # optional; project uses the Gradle wrapper
```

</details>

### 2) Per‑project setup (new project scaffold)

<details><summary>Show per-project setup</summary>

When starting a brand‑new Spring Boot project, generate via Spring Initializr (terminal).

Terminal (downloads a zip, unzips into current dir):

```bash
curl -fsSL https://start.spring.io/starter.zip \
  -d type=gradle-project \
  -d language=java \
  -d javaVersion=21 \
  -d packaging=jar \
  -d name=app \
  -d groupId=com.example \
  -d artifactId=app \
  -d dependencies=web,actuator,testcontainers \
  -d baseDir=app \
  -o app.zip
unzip -q app.zip
rsync -a app/ .
rm -rf app app.zip
```

</details>

### 3) Working with this project (repeatable)

**Available commands:**

### Using Make (recommended - convenient shortcuts)

```bash
# Development & Application
make dev              # Start Spring Boot application (development mode with hot reload)
make start            # Start Spring Boot application (production mode from JAR)
make stop             # Stop running application
make health           # Check application health

# Build & Testing
make build            # Build project (excluding tests)
make test             # Run tests
make package          # Create JAR file
make clean            # Clean build artifacts

# Code Quality - Main Workflow
make precommit        # Validate code quality without making changes
make fix              # Auto-fix code issues (formatting, linting, SpotBugs)

# Code Quality - Individual Tools
make format           # Check code formatting without changes
make format-fix       # Auto-format code

make lint             # Run all linting checks without changes
make lint-verbose     # Show detailed violations (auto-opens all reports)
make lint-fix         # Auto-fix common linting issues

# No "fix" for these, as they are not auto-fixable
make spotbugs         # Run SpotBugs bug detection (auto-opens report)
make pmd              # Run PMD code quality checks (auto-opens report)
```

### VS Code run button

- Run `./gradlew syncBin` (or any Gradle build) after pulling so `bin/main` mirrors the latest classes.
- Use `./gradlew runSimple -Pexample=<folder/ClassName>` for CLI runs; the VS Code ▶ button uses the same compiled output.

## Git Hooks (Automatic Quality Checks)

This project includes Git hooks that automatically run quality checks before each commit:

### Setup (One-time)

```bash
# Run the setup script
./scripts/setup-hooks.sh

# Or manually configure
git config core.hooksPath .githooks
chmod +x .githooks/pre-commit
```

### What Happens Automatically

- **Before each commit**: `make fix` runs to auto-fix issues
- **Then**: `make precommit` validates everything passes
- **If checks fail**: Commit is blocked until issues are resolved

### Emergency Override

```bash
# Skip hooks if absolutely necessary (not recommended)
git commit --no-verify
```

### Manual Testing

```bash
# Test the hooks manually
.githooks/pre-commit
```
