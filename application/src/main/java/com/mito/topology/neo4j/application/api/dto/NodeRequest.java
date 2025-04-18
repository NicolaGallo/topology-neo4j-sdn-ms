package com.mito.topology.neo4j.application.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeRequest {
    
    @NotBlank(message = "Type is required")
    private String type;
    
    @NotNull(message = "Properties are required (can be empty but not null)")
    private Map<String, Object> properties;
}