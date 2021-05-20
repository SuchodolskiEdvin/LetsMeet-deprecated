#!/usr/bin/env bash

# Skrypt pozwalający połączyć się z bazą danych postgresa na dockerze

docker exec -ti lets-meet-postgres sh -c "export PGPASSWORD=meet & psql -h localhost -p 5432 -U meet"
