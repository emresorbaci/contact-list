@echo off
start cmd /c start-backend
timeout 5 > NUL
start cmd /c start-ui