package com.example.appgithubrepo.infrastructure.controller.dto;

import java.util.List;

public record ReposResponseDto(String nameRepository, UserResponseDto userResponseDto,
                               List<BranchResponseDto> branches) {
}