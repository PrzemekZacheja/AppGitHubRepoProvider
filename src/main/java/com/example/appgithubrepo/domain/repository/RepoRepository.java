package com.example.appgithubrepo.domain.repository;

import com.example.appgithubrepo.domain.model.RepoFromGithub;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RepoRepository {

    final Map<Integer, RepoFromGithub> repoMap = new HashMap<>();

}