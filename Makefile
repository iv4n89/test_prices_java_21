# Name of the Docker image
IMAGE_NAME=price-service-app

# Name of the Docker test image
TEST_IMAGE_NAME=price-service-app-test

# Docker container name
CONTAINER_NAME=price-service-app-container

# Docker test container name
TEST_CONTAINER_NAME=price-service-app-test-container

# Dockerfile location
DOCKERFILE_PATH=./Dockerfile

# Dockerfile test location
DOCKERFILE_TEST_PATH=./DockerfileTest

# Build Docker image
build:
	docker build -t $(IMAGE_NAME) -f $(DOCKERFILE_PATH) .

# Run Docker container
run:
	docker run --name $(CONTAINER_NAME) -p 8081:8081 -d $(IMAGE_NAME)

# Build Docker test image
build-tests:
	docker build -t $(TEST_IMAGE_NAME) -f $(DOCKERFILE_TEST_PATH) .

# Run tests
run-tests:
	docker run --name $(TEST_CONTAINER_NAME) -d $(TEST_IMAGE_NAME)
	docker exec -it $(TEST_CONTAINER_NAME) mvn test
	docker stop $(TEST_CONTAINER_NAME)
	docker rm -f $(TEST_CONTAINER_NAME)

# Stop Docker container
stop-container:
	docker stop $(CONTAINER_NAME)

# Remove Docker container
rm:
	docker rm $(CONTAINER_NAME)

# Remove Docker image
rmi:
	docker rmi $(IMAGE_NAME)

# Start the project (build image, run container)
start: build run

# Run tests (build image, run tests)
test: build-tests run-tests

# Stop the project (stop container, remove container, remove image)
stop: stop-container rm rmi
