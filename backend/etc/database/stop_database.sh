#!/usr/bin/env bash

if docker stop lets-meet-postgres; then

	echo "Kontener z postgresem został zatrzymany";

else

	echo "Kontener lets-meet-postgres nie jest uruchomiony lub nie został utworzony!";

fi
