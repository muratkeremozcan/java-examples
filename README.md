## Java Dev Environment + Spring Boot (Quick, Repeatable)

This repo captures a minimal, modern Java setup you can reuse for future projects.

- Java: 21 (LTS)
- Build: Gradle 8 (wrapper included)
- Framework: Spring Boot 3.x
- IDE: IntelliJ IDEA (recommended)

### 1) One‑time setup on your machine

<details><summary>Show one‑time setup (Homebrew + JAVA_HOME)</summary>

```bash
brew install --cask temurin@21
brew install gradle maven
brew install --cask intellij-idea-ce  # or intellij-idea

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

### 3) Working with this project (repeatable)

From the project root:

Build (wrapper downloads Gradle automatically):

```bash
./gradlew build -x test
```

Run the app:

```bash
./gradlew run
```

Verify health:

```bash
curl http://localhost:8080/actuator/health
# Expected: {"status":"UP"}
```

Run tests:

```bash
./gradlew test
```

Stop the app:

- If running in foreground: Ctrl+C
- If backgrounded: `pkill -f 'org.springframework.boot' || pkill -f 'JarLauncher'`

### 4) Adding new examples

This project uses standard Maven/Gradle structure:

```
src/
├── main/
│   ├── java/
│   │   └── intro/
│   │       ├── lesson01/
│   │       │   └── WelcomeToJava.java
│   │       ├── lesson02/
│   │       │   └── Variables.java
│   │       └── lesson03/
│   │           └── Loops.java
│   └── resources/
└── test/
    ├── java/
    └── resources/
```

**To add a new example:**

1. Create directory: `mkdir -p src/main/java/intro/lesson02`
2. Create file: `src/main/java/intro/lesson02/Variables.java`
3. Add package declaration: `package intro.lesson02;`
4. Run it: `./gradlew runExample --args="intro.lesson02.Variables"`

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
```

### Notes

- Gradle wrapper (`./gradlew`) is preferred; no need to install Gradle globally.
- Keep JDK at 21 for Spring Boot 3.x unless you have constraints.
- Docker + Testcontainers are optional but recommended for integration testing.
- Use standard Maven/Gradle directory structure for better IDE support.
