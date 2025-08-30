SHELL := /bin/zsh

.PHONY: dev build test package clean health stop lint format precommit checkFormat listExamples runExample quickRun

dev:
	./gradlew bootRun

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

format:
	./gradlew format

checkFormat:
	./gradlew checkFormat

precommit:
	./gradlew preCommit

listExamples:
	./gradlew listExamples

runExample:
	@echo "Usage: make runExample className=example.lesson02.Hello"
	@echo "Example: make runExample className=01_intro/WelcomeToJava"
	./gradlew runExample -PclassName=$(className)

quickRun:
	@echo "Usage: make quickRun className=ClassName"
	@echo "Example: make quickRun className=WelcomeToJava"
	./gradlew quickRun -PclassName=$(className)

health:
	curl -fsS http://localhost:8080/actuator/health || true

stop:
	pkill -f 'org.springframework.boot' || pkill -f 'JarLauncher' || true


