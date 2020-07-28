#Pre-requisites
#Docker daemon should be running
echo $1
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=$1 -d mysql:8
