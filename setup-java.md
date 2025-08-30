# Java Setup Instructions

## 1. Install Java 21

First, you need to install Java and set up your environment:

```bash
# Install Java 21 (Temurin) - will require password
brew install --cask temurin@21

# Set JAVA_HOME (add to ~/.zshrc)
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc

# Verify installation
java -version
```

## 2. Test Basic Build

Once Java is installed:

```bash
# Test compilation
./gradlew compileJava

# Test your examples
./gradlew runSimple -Pexample=01_intro/WelcomeToJava
```

## 3. Run Linting and Code Quality

After basic build works:

```bash
# Run all quality checks
./gradlew lint

# Individual checks
./gradlew checkstyleMain
./gradlew pmdMain  
./gradlew spotbugsMain

# Full pre-commit validation
./gradlew preCommit
```

## 4. Install Code Formatter (Optional)

For automatic code formatting:

```bash
brew install google-java-format

# Format all Java files
find src -name '*.java' | xargs google-java-format --replace
```

## Issues Found & Fixed

1. **Syntax error**: Fixed `voi` -> `void` in WelcomeToJava.java
2. **SpotBugs config**: Removed invalid `source` property configuration
3. **Java not installed**: Need to install Java 21 first

Run these commands in order and the CI should pass!