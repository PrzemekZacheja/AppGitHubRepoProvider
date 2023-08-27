package com.example.appgithubrepo.domain.clientgithub.dto;

import java.util.List;

public record RepoFromGithubDto(String name, Owner owner, List<BranchDto> branches) {
}