run-dist:
	@./build/install/app/bin/app

help:
	@./build/install/app/bin/app -h

clean:
	@./gradlew clean

install:
	@./gradlew clean install

lint:
	@./gradlew checkstyleMain checkstyleTest

test:
	@./gradlew test

build:
	@./gradlew clean build

report:
	@./gradlew jacocoTestReport

.PHONY: build
