run-all-docker:
	docker-compose -f superstore-usuario/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-vendas/docker/docker-compose.yml 	up -d
	docker-compose -f superstore-produto/docker/docker-compose.yml up -d

deploy-docker-aws:
	scp -i "../AWS/aws-jonas.pem" ./superstore-produto/docker/docker-compose.yml ubuntu@ec2-3-88-66-131.compute-1.amazonaws.com:~/superstore-produto/docker-compose.yml
 	scp -i "../AWS/aws-jonas.pem" ./superstore-usuario/docker/docker-compose.yml ubuntu@ec2-3-88-66-131.compute-1.amazonaws.com:~/superstore-usuario/docker-compose.yml
 	scp -i "../AWS/aws-jonas.pem" ./superstore-vendas/docker/docker-compose.yml ubuntu@ec2-3-88-66-131.compute-1.amazonaws.com:~/superstore-vendas/docker-compose.yml

