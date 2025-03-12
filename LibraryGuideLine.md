KTOR client library for KMP to support ios,android, and desktop app.
Engine should be based onplatform type.
API request support should be both Graphql and Rest API
Should support to change microservice base URL based on need.
Should have support to send headers, query parameters, and body parameters.
all the arguments for graphql mutation parameters name should be dynamic. parent param of variables should be like "args"
To request any graphql api query and mutation should be supported as parameter.
graphql request method type should be POST.
Should have support to send files as part of the request. as an separate method in library.
Request should retry mechanism with exponential backoff with 2 max retry as a constants.
Should have support to cancel the request.
Should have support to add interceptors.
Response handling : Response should have common data class to handle success and error response. Actual response should be part of success response with generic type.
