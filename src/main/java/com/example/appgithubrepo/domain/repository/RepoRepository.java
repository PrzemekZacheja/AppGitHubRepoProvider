package com.example.appgithubrepo.domain.repository;

import com.example.appgithubrepo.domain.model.Repo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RepoRepository {

    final Map<Integer, Repo> repoMap = new HashMap<>();

    public List<Repo> listOfRepos(String userName) {
        return repoMap.values().stream().filter(repo -> repo.name().equals(userName)).toList();
    }
}