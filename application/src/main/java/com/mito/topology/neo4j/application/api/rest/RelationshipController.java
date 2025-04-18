package com.mito.topology.neo4j.application.api.rest;

import com.mito.topology.neo4j.application.api.dto.RelationshipDto;
import com.mito.topology.neo4j.application.api.dto.RelationshipRequest;
import com.mito.topology.neo4j.domain.model.Relationship;
import com.mito.topology.neo4j.domain.port.RelationshipPort;
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
@RequestMapping("/api/relationships")
@RequiredArgsConstructor
@Tag(name = "Relationships", description = "Relationship management APIs")
public class RelationshipController {

    private final RelationshipPort relationshipPort;

    @PostMapping
    @Operation(summary = "Create a new relationship", description = "Creates a new relationship between two nodes")
    public ResponseEntity<RelationshipDto> createRelationship(@Valid @RequestBody RelationshipRequest request) {
        Relationship relationship = relationshipPort.createRelationship(
                request.getType(),
                request.getSourceNodeId(),
                request.getTargetNodeId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(relationship));
    }

    @GetMapping
    @Operation(summary = "Get all relationships", description = "Retrieves all relationships from the database")
    public ResponseEntity<List<RelationshipDto>> getAllRelationships() {
        List<Relationship> relationships = relationshipPort.getAllRelationships();
        List<RelationshipDto> relationshipDtos = relationships.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(relationshipDtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a relationship by ID", description = "Retrieves a relationship by its ID")
    public ResponseEntity<RelationshipDto> getRelationshipById(@PathVariable Long id) {
        return relationshipPort.getRelationshipById(id)
                .map(relationship -> ResponseEntity.ok(mapToDto(relationship)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship not found with ID: " + id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a relationship", description = "Updates an existing relationship")
    public ResponseEntity<RelationshipDto> updateRelationship(@PathVariable Long id, @Valid @RequestBody RelationshipRequest request) {
        Relationship relationship = relationshipPort.updateRelationship(
                id,
                request.getType(),
                request.getSourceNodeId(),
                request.getTargetNodeId()
        );
        return ResponseEntity.ok(mapToDto(relationship));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a relationship", description = "Deletes a relationship by its ID")
    public ResponseEntity<Void> deleteRelationship(@PathVariable Long id) {
        relationshipPort.deleteRelationship(id);
        return ResponseEntity.noContent().build();
    }

    private RelationshipDto mapToDto(Relationship relationship) {
        return RelationshipDto.builder()
                .id(relationship.getId())
                .type(relationship.getType())
                .sourceNodeId(relationship.getSourceNodeId())
                .targetNodeId(relationship.getTargetNode().getId())
                .build();
    }
}