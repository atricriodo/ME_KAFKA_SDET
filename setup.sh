#!/bin/bash

# Get the host IP address
export HOST_IP="$(curl http://checkip.amazonaws.com/)"

echo "HOST_IP=$HOST_IP" > .env

# Restart docker containers
docker-compose down
docker-compose up -d --build

docker-compose logs -f --tail=50 qscore