package com.example.appgithubrepo.infrastructure.controller.dto;

import java.util.List;

public record ReposResponseDto(String name, UserDto userDto, List<BranchDto> branches) {
}