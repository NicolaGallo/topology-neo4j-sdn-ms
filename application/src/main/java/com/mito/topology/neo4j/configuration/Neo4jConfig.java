package com.mito.topology.neo4j.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.mito.topology.neo4j.entity.repository")
@EnableTransactionManagement
public class Neo4jConfig {
    // Non abbiamo bisogno di configurare manualmente il transaction manager
    // Spring Boot lo configurerà automaticamente in base al tipo di repository che stiamo utilizzando
}