package com.mito.topology.neo4j.application.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {
    private Long id;
    private String type;
    private Map<String, Object> properties;
}