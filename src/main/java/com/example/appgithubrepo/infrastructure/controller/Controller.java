package com.example.appgithubrepo.infrastructure.controller;

import com.example.appgithubrepo.domain.ReposProvider;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/repo")
@AllArgsConstructor
public class Controller {

    ReposProvider reposProvider;

    @GetMapping
    public ResponseEntity<List<ReposResponseDto>> getAllRepos(@RequestParam String userName,
                                                              @RequestHeader String accept) {
        List<ReposResponseDto> reposResponseDtos = reposProvider.getAllRepo(userName, accept);
        log.info("List of repos successfully returned from reposProvider");
        return ResponseEntity.ok(reposResponseDtos);
    }
}