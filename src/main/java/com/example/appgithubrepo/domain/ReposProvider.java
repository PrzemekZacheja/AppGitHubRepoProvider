package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.repository.RepoRepository;
import com.example.appgithubrepo.domain.service.BranchSshIncluder;
import com.example.appgithubrepo.domain.service.GitHubConnector;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ReposProvider {

    private RepoRepository repoRepository;
    private GitHubConnector gitHubConnector;
    private BranchSshIncluder branchSshIncluder;

    public List<ReposResponseDto> getAllRepo(String userName, String header) {
        List<RepoFromGithubDto> repoFromGithubDtos = gitHubConnector.takeReposFromGitHub(userName, header);
        List<Repo> repoListWithoutBranches = repoFromGithubDtos.stream().map(RepoMapper::mapFromRepoFromGitHubToRepo).toList();
        List<Repo> completeRepos = branchSshIncluder.includeBranchesSsh(repoListWithoutBranches);
        List<ReposResponseDto> reposResponseDtoList = completeRepos.stream().map(RepoMapper::mapFromRepoToReposResponseDto).toList();
        log.info("list of completeRepos successfully provide");
        saveRepoList(reposResponseDtoList);
        return reposResponseDtoList;
    }

    private void saveRepoList(List<ReposResponseDto> repoList) {
        if (!repoList.isEmpty()) {
            repoRepository.saveAll(repoList);
            log.info("list of repos successfully saved to the database");
        }
    }
}