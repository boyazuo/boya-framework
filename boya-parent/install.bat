@echo off
echo [INFO] Install parent pom.xml to local repository.

%~d0
cd %~dp0
call mvn clean install -Dmaven.test.skip=true
pause