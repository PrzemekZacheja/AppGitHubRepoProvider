package com.example.appgithubrepo.domain.repository;

import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RepoRepository {

    final Map<String, List<ReposResponseDto>> repoMap = new ConcurrentHashMap<>();

    public void saveAll(List<ReposResponseDto> repoList) {
        repoMap.put(repoList.get(0).userResponseDto().login(), repoList);
    }
}