package com.example.appgithubrepo.domain.clientgithub.dto;

import java.util.List;

public record BranchDto(String name, List<CommitDto> commit) {
}