package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.repository.RepoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReposProvider {
    private RepoRepository repoRepository;

    public String getAllRepo(String userName) {
        return repoRepository.getAllRepo(userName);
    }
}