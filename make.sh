#!/bin/bash

IMAGE_NAME="price-service-app"
TEST_IMAGE_NAME="price-service-app-test"
CONTAINER_NAME="price-service-app-container"
TEST_CONTAINER_NAME="price-service-app-test-container"
DOCKERFILE_PATH="./Dockerfile"
DOCKERFILE_TEST_PATH="./DockerfileTest"

case "$1" in
    build)
        docker build -t $IMAGE_NAME -f $DOCKERFILE_PATH .
        ;;
    run)
        docker run --name $CONTAINER_NAME -p 8081:8081 -d $IMAGE_NAME
        ;;
    build-tests)
        docker build -t $TEST_IMAGE_NAME -f $DOCKERFILE_TEST_PATH .
        ;;
    run-tests)
        docker run --name $TEST_CONTAINER_NAME -d $TEST_IMAGE_NAME
        docker exec -it $TEST_CONTAINER_NAME mvn test
        docker stop $TEST_CONTAINER_NAME
        docker rm -f $TEST_CONTAINER_NAME
        ;;
    stop-container)
        docker stop $CONTAINER_NAME
        ;;
    rm)
        docker rm $CONTAINER_NAME
        ;;
    rmi)
        docker rmi $IMAGE_NAME
        ;;
    start)
        $0 build
        $0 run
        ;;
    test)
        $0 build-tests
        $0 run-tests
        ;;
    stop)
        $0 stop-container
        $0 rm
        $0 rmi
        ;;
    *)
        echo "Comando no reconocido: $1"
        ;;
esac
