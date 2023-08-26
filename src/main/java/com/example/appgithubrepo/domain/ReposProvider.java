package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.repository.RepoRepository;
import com.example.appgithubrepo.domain.service.BranchSshProvider;
import com.example.appgithubrepo.domain.service.GitHubConectors;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ReposProvider {

    private RepoRepository repoRepository;
    private GitHubConectors gitHubConectors;
    private BranchSshProvider branchSshProvider;

    public List<ReposResponseDto> getAllRepo(String userName, String header) {
        List<RepoFromGithubDto> repoFromGithubDtos = gitHubConectors.takeReposFromGitHub(userName, header);
        List<Repo> repoList = repoFromGithubDtos.stream().map(RepoMapper::mapFromReposResponseDtoToRepo).toList();
        saveRepoList(repoList);


        List<Repo> allRepo = repoRepository.listOfRepos(userName);
        List<ReposResponseDto> reposResponseDtoList = allRepo.stream().map(RepoMapper::mapFromRepoToReposResponseDto).toList();
        log.info("list of repos successfully returned from the database");
        return reposResponseDtoList;

    }

    private List<Repo> saveRepoList(List<Repo> repoList) {
        if (!repoList.isEmpty()) {
            repoRepository.saveAll(repoList);
            log.info("list of repos successfully saved to the database");
            return repoList;
        }
        return Collections.emptyList();
    }
}