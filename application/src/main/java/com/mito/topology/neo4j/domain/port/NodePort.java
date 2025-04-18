package com.mito.topology.neo4j.domain.port;

import com.mito.topology.neo4j.domain.model.GraphNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NodePort {

    GraphNode createNode(String type, Map<String, Object> properties);
    
    List<GraphNode> getAllNodes();
    
    Optional<GraphNode> getNodeById(Long id);
    
    GraphNode updateNode(Long id, String type, Map<String, Object> properties);
    
    void deleteNode(Long id);
}