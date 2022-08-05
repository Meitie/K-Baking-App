#! /bin/bash

testing(){
  mvn initialize
  mvn clean
  mvn test
  # shellcheck disable=SC2181
  if [[ "$?" -ne 0 ]] ; then
      echo "Test failed"; exit 1
    else
      echo "Tests pass"
    fi
}

refresh_DB(){
  # shellcheck disable=SC2216
  yes | cp ./lib/quoteDB.db ./
  echo "Refreshed quoteDB.db"
}

package_target(){
  mvn package -Dmaven.test.skip
  echo "Project has been packaged to target"
}

build_docker(){
  docker build -t qserver .
}

run_docker(){
  docker run -p 5000:5000 qserver
}

run(){
  java -jar ./target/server-1.0-jar-with-dependencies.jar
}

clean_run(){
  testing
  refresh_DB
  package_target
  run
}

clean_docker(){
  testing
  refresh_DB
  package_target
  build_docker
  run_docker
}


case $1 in
  "testing") testing;;
  "refresh_DB") refresh_DB;;
  "package_target") package_target;;
  "run") run;;
  "clean_run") clean_run;;
  "build_docker") build_docker;;
  "run_docker") run_docker;;
  "clean_docker") clean_docker;;
esac