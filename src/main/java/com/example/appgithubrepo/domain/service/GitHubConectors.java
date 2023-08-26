package com.example.appgithubrepo.domain.service;

import com.example.appgithubrepo.domain.clientgithub.GitHubClient;
import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GitHubConectors {

    GitHubClient gitHubClient;

    public List<RepoFromGithubDto> takeReposFromGitHub(String userName, String header) {
        return gitHubClient.getRepos(userName, header);
    }
}