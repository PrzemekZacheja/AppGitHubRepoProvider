package com.example.appgithubrepo.domain.model;

import com.example.appgithubrepo.githubclient.domain.clientgithub.dto.OwnerDto;

public record RepoFromGithub(String name, Owner owner) {
}