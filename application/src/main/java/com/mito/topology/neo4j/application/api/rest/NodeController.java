package com.mito.topology.neo4j.application.api.rest;

import com.mito.topology.neo4j.application.api.dto.NodeDto;
import com.mito.topology.neo4j.application.api.dto.NodeRequest;
import com.mito.topology.neo4j.domain.model.GraphNode;
import com.mito.topology.neo4j.domain.port.NodePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nodes")
@RequiredArgsConstructor
@Tag(name = "Nodes", description = "Node management APIs")
public class NodeController {

    private final NodePort nodePort;

    @PostMapping
    @Operation(summary = "Create a new node", description = "Creates a new node with dynamic labels based on the type")
    public ResponseEntity<NodeDto> createNode(@Valid @RequestBody NodeRequest request) {
        GraphNode node = nodePort.createNode(request.getType(), request.getProperties());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(node));
    }

    @GetMapping
    @Operation(summary = "Get all nodes", description = "Retrieves all nodes from the database")
    public ResponseEntity<List<NodeDto>> getAllNodes() {
        List<GraphNode> nodes = nodePort.getAllNodes();
        List<NodeDto> nodeDtos = nodes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(nodeDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a node by ID", description = "Retrieves a node by its ID")
    public ResponseEntity<NodeDto> getNodeById(@PathVariable Long id) {
        return nodePort.getNodeById(id)
                .map(node -> ResponseEntity.ok(mapToDto(node)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Node not found with ID: " + id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a node", description = "Updates an existing node with dynamic labels based on the type")
    public ResponseEntity<NodeDto> updateNode(@PathVariable Long id, @Valid @RequestBody NodeRequest request) {
        GraphNode node = nodePort.updateNode(id, request.getType(), request.getProperties());
        return ResponseEntity.ok(mapToDto(node));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a node", description = "Deletes a node by its ID")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodePort.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    private NodeDto mapToDto(GraphNode node) {
        return NodeDto.builder()
                .id(node.getId())
                .type(node.getType())
                .properties(node.getProperties())
                .build();
    }
}