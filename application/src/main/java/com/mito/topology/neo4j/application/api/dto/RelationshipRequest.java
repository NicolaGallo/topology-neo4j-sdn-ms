package com.mito.topology.neo4j.application.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipRequest {
    
    @NotBlank(message = "Type is required")
    private String type;
    
    @NotNull(message = "Source node ID is required")
    private Long sourceNodeId;
    
    @NotNull(message = "Target node ID is required")
    private Long targetNodeId;
}