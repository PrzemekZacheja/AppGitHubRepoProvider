package com.example.appgithubrepo.domain.repository;

import com.example.appgithubrepo.domain.model.SimpleRepo;
import org.springframework.data.repository.Repository;

public interface RepoRepository extends Repository<SimpleRepo, Long> {


    void save(SimpleRepo simpleRepo);
}