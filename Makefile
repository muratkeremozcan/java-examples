SHELL := /bin/zsh

.PHONY: dev build test package clean health stop lint format precommit

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

precommit:
	./gradlew preCommit

health:
	curl -fsS http://localhost:8080/actuator/health || true

stop:
	pkill -f 'org.springframework.boot' || pkill -f 'JarLauncher' || true


