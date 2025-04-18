package com.mito.topology.neo4j.entity.service;

import com.mito.topology.neo4j.domain.model.Relationship;
import com.mito.topology.neo4j.domain.port.RelationshipPort;
import com.mito.topology.neo4j.entity.repository.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelationshipService implements RelationshipPort {

    private final RelationshipRepository relationshipRepository;

    @Override
    @Transactional
    public Relationship createRelationship(String type, Long sourceNodeId, Long targetNodeId) {
        return relationshipRepository.createRelationship(type, sourceNodeId, targetNodeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Relationship> getAllRelationships() {
        return relationshipRepository.findAllRelationships();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Relationship> getRelationshipById(Long id) {
        return relationshipRepository.findRelationshipById(id);
    }

    @Override
    @Transactional
    public Relationship updateRelationship(Long id, String type, Long sourceNodeId, Long targetNodeId) {
        return relationshipRepository.updateRelationship(id, type, sourceNodeId, targetNodeId);
    }

    @Override
    @Transactional
    public void deleteRelationship(Long id) {
        relationshipRepository.deleteRelationshipById(id);
    }
}