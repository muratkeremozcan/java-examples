SHELL := /bin/zsh

.PHONY: dev build test package clean health stop start lint format precommit checkFormat lint-verbose lint-fix fix spotbugs

dev:
	./gradlew bootRun

start:
	@echo "Starting application from built JAR..."
	./gradlew bootJar
	java -jar build/libs/app-0.0.1.jar

build:
	./gradlew build -x test

test:
	./gradlew test

package:
	./gradlew bootJar

clean:
	./gradlew clean

lint:
	./gradlew lint

lint-verbose:
	@echo "=== Running PMD with verbose output ==="
	./gradlew pmdMain --console=verbose || true
	@echo ""
	@echo "=== Running Checkstyle with verbose output ==="
	./gradlew checkstyleMain --console=verbose || true
	@echo ""
	@echo "=== Running SpotBugs ==="
	./gradlew spotbugsMain || true
	@echo ""
	@echo "=== Summary: Check reports for details ==="
	@echo "PMD report: build/reports/pmd/main.html"
	@echo "Checkstyle report: build/reports/checkstyle/main.html"
	@echo "SpotBugs report: build/reports/spotbugs/main.html"
	@echo ""
	@echo "📊 Opening reports in browser..."
	open build/reports/pmd/main.html || true
	open build/reports/checkstyle/main.html || true
	open build/reports/spotbugs/main.html || true

lint-fix:
	@echo "=== Auto-fixing common linting issues ==="
	@echo "1. Formatting code..."
	./gradlew format
	@echo "2. Adding final keywords where possible..."
	./gradlew compileJava
	@echo "3. Running SpotBugs analysis..."
	./gradlew spotbugsMain
	@echo "4. Running lint to verify fixes..."
	./gradlew lint
	@echo "=== Lint auto-fix complete! ==="

fix:
	@echo "=== Auto-fixing code quality issues ==="
	@echo "1. Formatting code..."
	./gradlew format
	@echo "2. Auto-fixing common linting issues..."
	./gradlew compileJava
	@echo "3. Running SpotBugs analysis..."
	./gradlew spotbugsMain
	@echo "4. Verifying fixes..."
	./gradlew lint
	@echo "=== All auto-fixes complete! ==="
	@echo "Note: Some issues may require manual fixes. Run 'make lint-verbose' to see details."

format:
	./gradlew format

checkFormat:
	./gradlew checkFormat

spotbugs:
	@echo "🔍 Running SpotBugs analysis..."
	./gradlew spotbugsMain
	@echo "📊 Opening SpotBugs report..."
	open build/reports/spotbugs/main.html

pmd:
	@echo "🔍 Running PMD analysis..."
	./gradlew pmdMain --console=verbose
	@echo "📊 Opening PMD report..."
	open build/reports/pmd/main.html

precommit:
	@echo "=== Running full pre-commit validation ==="
	@echo "1. Formatting check..."
	./gradlew checkFormat
	@echo "2. Code quality checks..."
	./gradlew lint
	@echo "3. SpotBugs analysis..."
	./gradlew spotbugsMain
	@echo "4. Full validation pipeline..."
	./gradlew preCommit
	@echo "=== All pre-commit checks passed! ==="

health:
	curl -fsS http://localhost:8080/actuator/health || true

stop:
	pkill -f 'org.springframework.boot' || pkill -f 'JarLauncher' || true


