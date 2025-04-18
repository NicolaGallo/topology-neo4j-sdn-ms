package com.mito.topology.neo4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RelationshipProperties
public class Relationship {
    @Id
    @GeneratedValue
    private Long id;

    private Long relationshipId; // Aggiungi questo campo per memorizzare l'identity restituito da Neo4j

    private String type;
    private Long sourceNodeId;


    private Long targetNodeId;
}