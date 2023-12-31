# Assignment Places Service

## OpenAPI Links

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

## Assumptions

+ security is out of scope, including exposed actuator endpoints
+ the entity this service is centered around is called `place`
+ the "web app" wants to be able to list all places and call up details of a specific one
+ a dummy in-memory "DB" will be used for storing `places`
+ the provided example JSON is an agreed upon structure, i.e. a requirement
  + an `id` is added to each `place`

## Improvements TODO

+ fetch all `spaces` with pagination
+ search `spaces`, e.g. return only `id` and `label`
+ grouping opening hours in the BE (example, adding a group id for each day when applicable)
+ HATEOAS - a nicer API with sensible links
+ security
+ use Kotlin to do away with null threat
+ input validation
+ better exception handling and tracing
+ request tracing
+ improve time formatting and localization in the response
+ Swagger examples
+ more test coverage, check edge cases
+ separate DB model from DTO if useful

## Observability

http://localhost:8080/actuator

http://localhost:8080/actuator/prometheus