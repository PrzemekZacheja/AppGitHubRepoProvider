package com.example.appgithubrepo.infrastructure.controller;

import com.example.appgithubrepo.domain.ReposProvider;
import com.example.appgithubrepo.domain.error.HeaderErrorException;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/repo")
@AllArgsConstructor
public class Controller {

    ReposProvider reposProvider;

    @GetMapping
    public ResponseEntity<List<ReposResponseDto>> getAllRepos(@RequestParam String userName,
                                                              @RequestHeader String header) {
        if (header.equals("application/json")) {
            List<ReposResponseDto> reposResponseDtos = reposProvider.getAllRepo(userName, header);
            log.info("List of repos successfully returned from reposProvider");
            return ResponseEntity.ok(reposResponseDtos);
        }
        log.info(" header request " + header + " received - not supported");
        throw new HeaderErrorException(header + " not supported");
    }
}