@echo off
setlocal enabledelayedexpansion

:: Check if psql is in the PATH
where psql >nul 2>nul || (
  echo Please ensure that the PostgreSQL client is in your PATH
  exit /b 1
)

:: Create directory for PostgreSQL data
mkdir %USERPROFILE%\docker\volumes\postgres 2>nul

:: Remove existing PostgreSQL data
rmdir /s /q %USERPROFILE%\docker\volumes\postgres\data 2>nul

:: Start PostgreSQL in a Docker container
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=root -e POSTGRES_DB=dev -d -p 5432:5432 -v %USERPROFILE%\docker\volumes\postgres:/var/lib/postgresql postgres

:: Wait for PostgreSQL to start
ping localhost -n 4 >nul

:: Set the password for PostgreSQL
set PGPASSWORD=root

:: Run schema.sql to create database schema
psql -U postgres -d dev -h localhost -f schema.sql

:: Run data.sql to populate the database with data
psql -U postgres -d dev -h localhost -f data.sql

:: End of script