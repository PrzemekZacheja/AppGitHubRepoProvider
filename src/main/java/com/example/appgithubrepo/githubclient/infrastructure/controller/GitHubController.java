package com.example.appgithubrepo.githubclient.infrastructure.controller;

import com.example.appgithubrepo.githubclient.infrastructure.controller.dto.RepoFromGithubDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "github-client")
public interface GitHubController {

    @GetMapping("users/{userName}/repos")
    List<RepoFromGithubDto> getRepos(@PathVariable String userName,
                                     @RequestHeader String accept);
}