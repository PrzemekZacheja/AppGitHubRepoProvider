package com.example.appgithubrepo.domain.repository;

import com.example.appgithubrepo.domain.model.Repo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RepoRepository {

    final Map<String, List<Repo>> repoMap = new ConcurrentHashMap<>();

    public void saveAll(List<Repo> repoList) {
        repoMap.put(repoList.get(0).user().login(), repoList);
    }
}