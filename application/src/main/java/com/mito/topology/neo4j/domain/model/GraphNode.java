package com.mito.topology.neo4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node(primaryLabel = "BaseNode")
public class GraphNode {

    @Id
    @GeneratedValue
    private Long id;

    private String type;
    
    private Map<String, Object> properties;
}