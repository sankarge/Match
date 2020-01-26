 ```shell script
docker push gcr.io/gccloud-266311/match:v1

gcloud compute regions list
gcloud config set compute/region europe-west1
gcloud config set compute/zone europe-west1-b

gcloud container images list
gcloud container images list --repository=gcr.io/gccloud-26631
```