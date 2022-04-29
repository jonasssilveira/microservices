run-all-docker:
	docker-compose -f superstore-usuario/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-vendas/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-produtos/docker/docker-compose.yml up -d
