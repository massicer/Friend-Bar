test:
	./gradlew test

docker-local-build:
	./gradlew assemble --no-daemon
	docker build -f src/main/docker/Dockerfile.jvm -t friend-bar/local .

docker-local-run:
	docker run -i --rm -p 8080:8080 friend-bar/local

format:
	./gradlew ktlintFormat

lint:
	./gradlew ktlintCheck
