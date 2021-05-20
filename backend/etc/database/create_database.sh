#!/usr/bin/env bash

if docker create --name lets-meet-postgres -p 4000:5432 -e POSTGRES_DB=meet -e POSTGRES_USER=meet -e POSTGRES_PASSWORD=meet postgres:11.1 ; then
	echo "Utworzony został kontener z postgresem! Aby go uruchomić wykonaj skrypt start_database.sh"
else
	echo "Kontener o nazwie lets-meet-postgres już istnieje! Usuń go za pomocą skryptu delete_database.sh"
fi
