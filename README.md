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

```bash
# List all examples
./gradlew listExamples

# Run specific example
./gradlew runExample --args="intro.lesson01.WelcomeToJava"

# Run default (WelcomeToJava)
./gradlew run

# Build project
./gradlew build -x test

# Code quality and linting
./gradlew lint           # Run all linting checks (Google Style + PMD + SpotBugs)
./gradlew checkstyleMain # Google Java Style checks
./gradlew pmdMain        # Static analysis
./gradlew spotbugsMain   # Bug detection  
./gradlew preCommit      # Full validation pipeline

# Format code (built into Gradle - no external tools needed)
./gradlew format          # Auto-format with Google Java Style
```
