test:
	./gradlew test

docker-local-build:
	./gradlew build
	docker build -f src/main/docker/Dockerfile.jvm -t friend-bar/local .

docker-local-run:
	docker run -i --rm -p 8080:8080 friend-bar/local