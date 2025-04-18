package com.mito.topology.neo4j.domain.port;

import com.mito.topology.neo4j.domain.model.Relationship;

import java.util.List;
import java.util.Optional;

public interface RelationshipPort {

    Relationship createRelationship(String type, Long sourceNodeId, Long targetNodeId);
    
    List<Relationship> getAllRelationships();
    
    Optional<Relationship> getRelationshipById(Long id);
    
    Relationship updateRelationship(Long id, String type, Long sourceNodeId, Long targetNodeId);
    
    void deleteRelationship(Long id);
}