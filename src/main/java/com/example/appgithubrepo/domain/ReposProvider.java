package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.model.SimpleRepo;
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
        List<Repo> repoListWithoutBranches = repoFromGithubDtos.stream()
                                                               .map(RepoMapper::mapFromRepoFromGitHubToRepo)
                                                               .toList();
        List<Repo> completeRepos = branchSshIncluder.includeBranchesSsh(repoListWithoutBranches);
        List<SimpleRepo> simpleRepos = RepoMapper.mapFromReposToSimpleRepos(completeRepos);
        saveSimpleRepoList(simpleRepos);
        List<ReposResponseDto> completeReposResponseDtoList = completeRepos.stream()
                                                                           .map(RepoMapper::mapFromRepoToReposResponseDto)
                                                                           .toList();
        log.info("list of complete repositories successfully provide with branches");
        return completeReposResponseDtoList;
    }

    private void saveSimpleRepoList(List<SimpleRepo> repoList) {
        if (!repoList.isEmpty()) {
            repoList.forEach(simpleRepo -> repoRepository.save(simpleRepo));
            log.info("list of simple repositories successfully save in DB");
        }
    }
}