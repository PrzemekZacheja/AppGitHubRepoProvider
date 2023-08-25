package com.example.appgithubrepo.infrastructure.controller;

import com.example.appgithubrepo.infrastructure.controller.dto.GetAllReposResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/repo")
public class GitHubController {

    @GetMapping
    public ResponseEntity<List<GetAllReposResponseDto>> getAllRepos(@RequestHeader String accept) {
        List<GetAllReposResponseDto> getAllReposResponseDtos = Collections.emptyList();
        return ResponseEntity.ok(getAllReposResponseDtos);
    }
}