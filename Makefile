run-all-docker:
	docker-compose -f superstore-usuario/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-vendas/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-produto/docker/docker-compose.yml up -d
