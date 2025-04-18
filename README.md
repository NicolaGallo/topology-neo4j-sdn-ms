# Topology Neo4j SDN Microservice

## Purpose
This microservice exposes a CRUD API to manage nodes and relationships in a Neo4j graph database using Spring Data Neo4j. It provides the capability to create, read, update, and delete nodes with dynamic labels, as well as create, read, update, and delete relationships between nodes.

## Features
- CRUD operations for nodes with dynamic labels
- CRUD operations for relationships
- Support for APOC procedures to create dynamic node labels
- Environment-specific configurations (LOCAL and PROD)

## How to run it

### Local Development
To run the service locally, you can use the provided docker-compose file:

```bash
docker-compose -f docker/topology-neo4j-sdn-ms-local.yml up
```

This will start both the microservice and a Neo4j database with the APOC plugin installed.

### Production
For production deployment, use:

```bash
docker-compose -f docker/topology-neo4j-sdn-ms-prod.yml up
```

This will start only the microservice, which will connect to an external Neo4j Aura database specified in the environment variables.

## Configuration

### Environment Variables
The following environment variables are used by the application:

#### Common
- `SPRING_PROFILES_ACTIVE`: Set to `local` for local development, `prod` for production

#### Local Environment
- `NEO4J_URI`: URI for the Neo4j database (defaults to `bolt://neo4j:7687`)
- `NEO4J_USERNAME`: Username for the Neo4j database (defaults to `neo4j`)
- `NEO4J_PASSWORD`: Password for the Neo4j database (defaults to `password`)

#### Production Environment
- `NEO4J_URI`: URI for the Neo4j Aura database
- `NEO4J_USERNAME`: Username for the Neo4j Aura database
- `NEO4J_PASSWORD`: Password for the Neo4j Aura database

## API Documentation
Once the service is running, you can access the API documentation at:
```
http://localhost:8080/swagger-ui.html
```

## Architecture
This microservice follows Hexagonal architecture and Domain Driven Design principles, structured according to the standard MITO DTS (Data Tree Structure).