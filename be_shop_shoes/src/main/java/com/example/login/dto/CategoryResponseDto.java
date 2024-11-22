package com.example.login.dto;

import java.util.List;

public class CategoryResponseDto {
    private Long id;
    private String name;
    private List<CategoryResponseDto> children;
    private Long parentId;

    public CategoryResponseDto(Long id, String name, List<CategoryResponseDto> children, Long parentId) {
        this.id = id;
        this.name = name;
        this.children = children;
        this.parentId = parentId;
    }

    public CategoryResponseDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public CategoryResponseDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(List<CategoryResponseDto> children) {
        this.children = children;
    }

    public List<CategoryResponseDto> getChildren() {
        return children;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
