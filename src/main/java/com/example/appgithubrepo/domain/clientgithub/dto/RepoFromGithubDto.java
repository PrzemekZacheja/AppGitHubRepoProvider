package com.example.appgithubrepo.domain.clientgithub.dto;

import com.example.appgithubrepo.infrastructure.controller.dto.BranchDto;

import java.util.List;

public record RepoFromGithubDto(String name, OwnerGitHubDto ownerGitHubDto, List<BranchGitHubDto> branches) {
}