package com.mito.topology.neo4j.entity.repository;

import com.mito.topology.neo4j.domain.model.Relationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends Neo4jRepository<Relationship, Long> {

    @Query("MATCH (source) WHERE ID(source) = $sourceNodeId " +
           "MATCH (target) WHERE ID(target) = $targetNodeId " +
           "CREATE (source)-[r:RELATED {type: $type}]->(target) " +
           "RETURN ID(r) as id, r.type as type, ID(source) as sourceNodeId, target as targetNode")
    Relationship createRelationship(
            @Param("type") String type,
            @Param("sourceNodeId") Long sourceNodeId,
            @Param("targetNodeId") Long targetNodeId);

    @Query("MATCH (source)-[r]->(target) " +
           "RETURN ID(r) as id, r.type as type, ID(source) as sourceNodeId, target as targetNode")
    List<Relationship> findAllRelationships();

    @Query("MATCH (source)-[r]->(target) WHERE ID(r) = $id " +
           "RETURN ID(r) as id, r.type as type, ID(source) as sourceNodeId, target as targetNode")
    Optional<Relationship> findRelationshipById(@Param("id") Long id);

    @Query("MATCH (source)-[r]->(target) WHERE ID(r) = $id " +
           "DELETE r " +
           "WITH source, target " +
           "MATCH (newSource) WHERE ID(newSource) = $sourceNodeId " +
           "MATCH (newTarget) WHERE ID(newTarget) = $targetNodeId " +
           "CREATE (newSource)-[newR:RELATED {type: $type}]->(newTarget) " +
           "RETURN ID(newR) as id, newR.type as type, ID(newSource) as sourceNodeId, newTarget as targetNode")
    Relationship updateRelationship(
            @Param("id") Long id,
            @Param("type") String type,
            @Param("sourceNodeId") Long sourceNodeId,
            @Param("targetNodeId") Long targetNodeId);

    @Query("MATCH ()-[r]->() WHERE ID(r) = $id DELETE r")
    void deleteRelationshipById(@Param("id") Long id);
}