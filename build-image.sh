mvn clean
cd web
npm run build
cd ..
mvn clean install
mvn compile jib:dockerBuild -Dimage=match:v1