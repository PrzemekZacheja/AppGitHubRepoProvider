package com.example.appgithubrepo;

import com.example.appgithubrepo.githubclient.infrastructure.controller.GitHubController;
import com.example.appgithubrepo.githubclient.infrastructure.controller.dto.RepoFromGithubDto;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Log4j2
@AllArgsConstructor
public class AppGitHubRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGitHubRepoApplication.class, args);
    }

    GitHubController gitHubController;

    @EventListener(ApplicationStartedEvent.class)
    public void doSomethingAfterStartup() {
        try {
            List<RepoFromGithubDto> przemekZacheja = gitHubController.getRepos("PrzemekZacheja", "application/json");
            log.info(przemekZacheja);
        } catch (FeignException e) {
            log.error(e.getMessage());
            log.error(e.getClass());
        }
    }
}