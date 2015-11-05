@echo off
echo [INFO] Install jar to local repository.

%~d0
cd %~dp0
call mvn clean install
pause