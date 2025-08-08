@echo off
set IMAGE_NAME=price-service-app
set TEST_IMAGE_NAME=price-service-app-test
set CONTAINER_NAME=price-service-app-container
set TEST_CONTAINER_NAME=price-service-app-test-container
set DOCKERFILE_PATH=./Dockerfile
set DOCKERFILE_TEST_PATH=./DockerfileTest

if "%1"=="build" goto build
if "%1"=="run" goto run
if "%1"=="build-tests" goto build-tests
if "%1"=="run-tests" goto run-tests
if "%1"=="stop-container" goto stop-container
if "%1"=="rm" goto rm
if "%1"=="rmi" goto rmi
if "%1"=="start" goto start
if "%1"=="test" goto test
if "%1"=="stop" goto stop
echo Comando no reconocido: %1
goto end

:build
docker build -t %IMAGE_NAME% -f %DOCKERFILE_PATH% .
goto end

:run
docker run --name %CONTAINER_NAME% -p 8081:8081 -d %IMAGE_NAME%
goto end

:build-tests
docker build -t %TEST_IMAGE_NAME% -f %DOCKERFILE_TEST_PATH% .
goto end

:run-tests
docker run --name %TEST_CONTAINER_NAME% -d %TEST_IMAGE_NAME%
docker exec -it %TEST_CONTAINER_NAME% mvn test
docker stop %TEST_CONTAINER_NAME%
docker rm -f %TEST_CONTAINER_NAME%
goto end

:stop-container
docker stop %CONTAINER_NAME%
goto end

:rm
docker rm %CONTAINER_NAME%
goto end

:rmi
docker rmi %IMAGE_NAME%
goto end

:start
call %0 build
call %0 run
goto end

:test
call %0 build-tests
call %0 run-tests
goto end

:stop
call %0 stop-container
call %0 rm
call %0 rmi
goto end

:end
