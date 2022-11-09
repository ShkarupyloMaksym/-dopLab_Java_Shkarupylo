name_container = shkarupylo_dop_lab_container
name_db_container = shkarupylo_dop_lab_postgres-db

run:
	docker compose up

createIMG:
	docker build -t shkarupylo_dop_lab .



test_withoutDocker:
	./gradlew test

removeDB:
	docker rm $(name_db_container)

removeLab:
	docker rm $(name_container)

removeLabIMG:
	docker rmi shkarupylo_dop_lab

stopLab:
	docker stop $(name_container)

stopDB:
	docker stop $(name_db_container)

startDB:
	docker start $(name_db_container)

startLab:
	docker start $(name_container)

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

refresh:
	make stopLab
	make startLab