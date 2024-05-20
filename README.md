# Repository to practice Java Quarkus Platform
## Running
### On Docker (locally with Docker Compose)
1. Make sure you have Docker and Docker Compose installed
2. From the root folder, run `chmod +x run-local.sh`
3. Then run `./run-local.sh`
4. To stop the services, run `./run-local.sh stop`

### On Kubernetes (locally with Kind)
1. Make sure you have Kind installed
2. Go to `infra/k8s/kind` folder
3. Run `./create-cluster.sh`
4. Move back to the `k8s` folder
5. Run `kubectl apply -f .` to all the resources
6. Run `./destroy-cluster.sh` to destroy the cluster

Or simply go to the root folder and run `./run-local-on-kind.sh`.  
Then `./run-local-on-kind.sh stop` to stop the services.

I'm using [Lens](https://k8slens.dev) to manage my Kubernetes cluster, but it's not necessary. You can use `kubectl` to do the same.

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
- [x] Caching (Redis)
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
- [ ] Coverage check

- [x] Docker Compose
- [x] CI (GitHub Actions)
- [ ] Sonar check
- [ ] CD
- [ ] Kubernetes (AWS)
- [x] Local Kubernetes ([Kind](https://kind.io))
- [x] Redis
- [ ] Observability
- [ ] Monitoring (Jaeger)