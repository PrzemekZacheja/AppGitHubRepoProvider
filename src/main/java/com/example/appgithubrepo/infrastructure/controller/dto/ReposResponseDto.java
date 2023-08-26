package com.example.appgithubrepo.infrastructure.controller.dto;

import java.util.List;

public record ReposResponseDto(String name, UserResponseDto userResponseDto, List<BranchResponseDto> branches) {
}