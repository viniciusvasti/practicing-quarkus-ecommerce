# Repository to practice Java Quarkus Platform
## Running locally
### On Kubernetes
1. Install Kind (https://kind.sigs.k8s.io/docs/user/quick-start/)
2. Create a cluster running the following command from the root of the project (edit the file to remove nodes if wanted):
```shell
kind create cluster --config=infra/k8s/kind.yaml
```
3. Build the app:
```shell
mvn clean install
```
or
```shell
mvn build
```
4. Build the image
```shell
quarkus image build docker
```
5. Run the Postgres database:
```shell
docker-compose -f infra/compose.dev.yaml up -d
```

## What Software Engineering Principles, Patterns, and Practices are being applied?
- [x] SOLID
- [x] Clean Code
- [x] Clean Architecture
- [x] DTO design pattern (Data Transfer Object)

- [x] Monolith
- [x] REST API (JAX-RS)
- [x] RESTFull API Maturity Level 3
- [x] API Specification (OpenAPI + Swagger)
- [x] SQL Database (Postgres)
- [ ] NoSQL
- [x] Database Migrations (Flyway)
- [ ] GraphQL API
- [x] Health Check (SmallRye / MicroProfile)
- [x] Caching
- [x] Application Event Bus
- [ ] Distributed Logging
- [ ] Microservices
- [ ] API Gateway
- [x] Synchronous Communication
- [ ] Asynchronous Communication
- [ ] Event-Driven
- [ ] Microservices Orchestration
- [ ] Microservices Choreography
- [ ] CQRS
- [ ] Event Sourcing
- [ ] Pagination

- [x] Unit Tests (JUnit)
- [x] Integration Tests (JUnit + Rest Assured)
- [x] End-to-End Tests (Karate)
- [x] Mocks (Mockito)
- [x] Stubs (Mockito)
- [x] Spies (Mockito)

- [x] Docker Compose
- [ ] CI/CD
- [ ] Kubernetes
- [x] Redis
- [ ] Observability