package com.example.appgithubrepo.domain.clientgithub;

import com.example.appgithubrepo.domain.clientgithub.dto.BranchDto;
import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "github-client")
public interface GitHubClient {

    @GetMapping("users/{userName}/repos")
    List<RepoFromGithubDto> getRepos(@PathVariable String userName,
                                     @RequestHeader String accept);

    @GetMapping("/{userName}/{repo}/branches")
    List<BranchDto> getBranches(@PathVariable String userName,
                                @PathVariable String repo);

}