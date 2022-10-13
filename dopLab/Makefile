run:
	docker compose up

createIMG:
	docker build -t shkarupylo_dop_lab .



test_withoutDocker:
	./gradlew test

removeDB:
	docker rm shkarupylo_dop_lab_postgres-db

removeLab:
	docker rm shkarupylo_dop_lab_container

removeLabIMG:
	docker rmi shkarupylo_dop_lab

stopLab:
	docker stop shkarupylo_dop_lab_container

stopDB:
	docker stop shkarupylo_dop_lab_postgres-db

startDB:
	docker start shkarupylo_dop_lab_postgres-db

startLab:
	docker start shkarupylo_dop_lab_container

runAll:
	make createIMG
	make run

start:
	make startDB
	make startLab

stop:
	make stopDB
	make stopLab

Stop_removeAll:
	make stop
	make removeLab
	make removeDB
	make removeLabIMG