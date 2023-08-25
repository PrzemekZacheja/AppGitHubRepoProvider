package com.example.appgithubrepo.infrastructure.controller.dto;

import com.example.appgithubrepo.domain.clientgithub.dto.OwnerDto;

import java.util.List;

public record GetAllReposResponseDto(String name, UserDto userDto, List<BranchDto> branches) {
}