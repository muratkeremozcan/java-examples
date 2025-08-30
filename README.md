## Java Dev Environment + Spring Boot (Quick, Repeatable)

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
# Development
make dev              # Start Spring Boot application
make build            # Build project (excluding tests)
make test             # Run tests
make package          # Create JAR file

# Examples and running
make listExamples     # Show all available example classes
make runExample className=ClassName  # Run specific example
make quickRun className=ClassName    # Quick compile and run

# Code quality and maintenance
make lint             # Run all linting checks
make format           # Auto-format code
make checkFormat      # Check formatting without changes
make precommit        # Full validation pipeline
make clean            # Clean build artifacts
make health           # Check application health
make stop             # Stop running application
```

### Using Gradle directly

```bash
# Examples and running
./gradlew listExamples                    # Show all available example classes
./gradlew runExample -PclassName=ClassName  # Run specific example
./gradlew quickRun -PclassName=ClassName    # Quick compile and run

# Build project
./gradlew build -x test                   # Build (excluding tests)
./gradlew clean                           # Clean build artifacts

# Code quality and linting
./gradlew lint                            # Run all linting checks (Google Style + PMD + SpotBugs)
./gradlew checkFormat                     # Check formatting without changes
./gradlew format                          # Auto-format with Google Java Style
./gradlew preCommit                       # Full validation pipeline

# Individual quality tools
./gradlew checkstyleMain                  # Google Java Style checks
./gradlew pmdMain                         # Static analysis
./gradlew spotbugsMain                    # Bug detection
```
