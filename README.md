#Support for retrieving tag metadata in timeseries-service
We would like to provide industrial analytics to our users using a third party product called TrendMiner. To enable this feature we need to implement an API to provide data to the third party. One of the required services must return tag metadata. Our timeseries-service is the API service that implements these endpoints.

##Request
###Endpoint 1
POST /api/v2/tags/addTagDetails

####Parameters
It accepts the list of tag details (historianName, Name, Description, Units, Type)

###Endpoint 2
GET /api/v2/tags/allTagDetails

This is endpoint returns the list of all the tag metadata persisted.

###Endpoint 3
GET /api/v2/tags

This is endpoint accepts input as path parameter. (Ex: GET /api/v2/tags/h1 where h1 is historianName)

It returns the list of tag details associated with the historianName provided as input.

It returns 500 Server Error when it does not find any tag details associated with given historianName.

####Parameters

Name | Type | Required| Description | Schema
------------ | ------------- | ------------- | ------------- | ------------- |  
historianName |Query | Yes | The name of the historian to list tags of. | string

### Response

HTTP Code | Description | Required | Schema
------------ | ------------- | ------------- | ------------- |  
200 | OK | Yes | Array of TagDetails | 
500 | Server Error | Yes | SystemException |

#### TagDetails

Name | Required | Description | Schema
------------ | ------------- | ------------- |  ------------- | 
Description | Yes | The description of the tag | string |
Name | Yes | The name of the tag | string |
Type | Yes | The tag type | enum (ANALOG, DIGITAL, DISCRETE, STRING) |
Units | Yes | The unit the tag belongs to | string |

### Example Response

```json
[
  {
    "Name":"React5_temp001",
    "Description":"Reactor 5 - Cooling water temperature",
    "Units":"C",
    "Type":"ANALOG"
  },
  {
    "Name":"React5_press",
    "Description":"Reactor 5 - Top pressure",
    "Units":"",
    "Type":"ANALOG"
  },
  {
    "Name":"React5_phas",
    "Description":"Reactor 5 - Production Phases",
    "Units":"",
    "Type":"STRING"
  }
]
```

## Built with
- Java 11
- Quarkus
- Gradle
- JUnit 5
- RestEasy
- H2 In-memory DB