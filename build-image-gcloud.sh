docker image rm -f eu.gcr.io/gccloud-266311/match:v1
mvn clean
cd web
npm run build
cd ..
mvn clean install
mvn compile jib:dockerBuild -Dimage=eu.gcr.io/gccloud-266311/match:v1