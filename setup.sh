#!/bin/bash

# Restart docker containers
docker-compose down --remove-orphans --volumes --rmi all
docker-compose up -d --build


echo "QScore API is at $(gp url 8081)"
echo "KafkaHQ is at $(gp url 8082)"
docker-compose logs -f --tail=30 qscore