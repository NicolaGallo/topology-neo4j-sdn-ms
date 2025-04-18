package com.mito.topology.neo4j.entity.repository;

import com.mito.topology.neo4j.domain.model.GraphNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface NodeRepository extends Neo4jRepository<GraphNode, Long> {

    @Query("MATCH (n) WHERE ID(n) = $id RETURN n")
    Optional<GraphNode> findNodeById(@Param("id") Long id);

    @Query("CREATE (n:BaseNode {type: $type}) " +
           "WITH n " +
           "CALL apoc.create.addLabels(n, split($type, ':')) YIELD node " +
           "WITH node AS n " +
           "RETURN n")
    GraphNode createNodeWithDynamicLabels(
            @Param("type") String type,
            @Param("properties") Map<String, Object> properties);

    @Query("MATCH (n) WHERE ID(n) = $id " +
           "SET n.type = $type " +
           "WITH n " +
           "CALL apoc.create.addLabels(n, split($type, ':')) YIELD node " +
           "WITH node AS n " +
           "RETURN n")
    GraphNode updateNodeWithDynamicLabels(
            @Param("id") Long id,
            @Param("type") String type,
            @Param("properties") Map<String, Object> properties);
}