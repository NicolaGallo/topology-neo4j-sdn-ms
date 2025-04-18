package com.mito.topology.neo4j.application.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipDto {
    private Long id;
    private String type;
    private Long sourceNodeId;
    private Long targetNodeId;
}