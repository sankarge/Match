# Match

## Match filter requests
```shell script
http://localhost:8080/matches/filter

http://localhost:9090/matches/filter?hasContact=1&hasPhoto=1&isFavourite=1

http://localhost:9090/matches/filter?distance=2&isFavourite=0

http://localhost:9090/matches/filter?distance=2&isFavourite=0&heightFrom=165&heightTo=175 \
&ageFrom40&ageTo=50&csFrom=80&csTo=88&hasPhoto=1&hasContact=1
```


## Mongo query
```json
{
  "query": {
    "$and": [
      {
        "age": {
          "$lte": 50
        }
      },
      {
        "compatibilityScore": {
          "$gte": 0.800000011920929,
          "$lte": 0.8799999952316284
        }
      },
      {
        "contactsExchanged": {
          "$gt": 0
        }
      },
      {
        "favourite": false
      },
      {
        "height": {
          "$gte": 165,
          "$lte": 175
        }
      },
      {
        "mainPhoto": {
          "$exists": true
        }
      }
    ]
  },
  "maxDistance": 3.135711885774796E-4,
  "distanceMultiplier": 6378.137,
  "near": [
    51.5071576,
    -0.1275761
  ],
  "spherical": true
}
```