#!/usr/bin/env bash

if docker rm -f lets-meet-postgres; then
	echo "Kontener z postgresem został usunięty! Utwórz nowy kontener za pomoca skryptu delete_database.sh";
else
	echo "Kontener lets-meet-postgres nie jest uruchomiony!";
fi
