package com.mito.topology.neo4j.entity.service;

import com.mito.topology.neo4j.domain.model.GraphNode;
import com.mito.topology.neo4j.domain.port.NodePort;
import com.mito.topology.neo4j.entity.repository.NodeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NodeService implements NodePort {

    private final NodeRepository nodeRepository;
    private final Neo4jClient neo4jClient; // Aggiungi questa dipendenza

    @Override
    @Transactional
    public GraphNode createNode(String type, Map<String, Object> properties) {
        GraphNode node = nodeRepository.createNodeWithDynamicLabels(type, properties);
        
        // Imposta le proprietà una per una
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            neo4jClient.query("MATCH (n) WHERE ID(n) = $id SET n." + entry.getKey() + " = $value")
                .bindAll(Map.of("id", node.getId(), "value", entry.getValue()))
                .run();
        }
        
        // Ricarica il nodo con tutte le proprietà
        return nodeRepository.findById(node.getId()).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GraphNode> getAllNodes() {
        return nodeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GraphNode> getNodeById(Long id) {
        return nodeRepository.findNodeById(id);
    }

    @Override
    @Transactional
    public GraphNode updateNode(Long id, String type, Map<String, Object> properties) {
        GraphNode node = nodeRepository.updateNodeWithDynamicLabels(id, type, properties);
        
        // Imposta le proprietà una per una
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            neo4jClient.query("MATCH (n) WHERE ID(n) = $id SET n." + entry.getKey() + " = $value")
                .bindAll(Map.of("id", node.getId(), "value", entry.getValue()))
                .run();
        }
        
        // Ricarica il nodo con tutte le proprietà
        return nodeRepository.findById(node.getId()).orElseThrow();
    }

    @Override
    @Transactional
    public void deleteNode(Long id) {
        nodeRepository.deleteById(id);
    }
}