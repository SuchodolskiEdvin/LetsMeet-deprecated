#!/usr/bin/env bash

if docker start lets-meet-postgres ; then
	echo "Uruchomienie kontenera z postgresem na porcie 4000 zakończone sukcesem"

else
	echo "Brak kontenera z postgresem! Aby utworzyć kontener wykonaj skrypt create_database.sh"
fi
