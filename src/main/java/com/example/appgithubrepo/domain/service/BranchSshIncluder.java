package com.example.appgithubrepo.domain.service;

import com.example.appgithubrepo.domain.RepoMapper;
import com.example.appgithubrepo.domain.clientgithub.GitHubClient;
import com.example.appgithubrepo.domain.clientgithub.dto.BranchDto;
import com.example.appgithubrepo.domain.model.Branch;
import com.example.appgithubrepo.domain.model.Repo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class BranchSshIncluder {

    GitHubClient gitHubClient;

    public List<Repo> includeBranchesSsh(List<Repo> repoList) {
        List<Repo> repoToReturn = new ArrayList<>(repoList);
        for (Repo repo : repoToReturn) {
            String repoName = repo.name();
            String userName = repo.user().login();
            List<BranchDto> branchesDto = gitHubClient.getBranches(userName, repoName);
            List<Branch> branchesFromGitHub = branchesDto.stream().map(RepoMapper::mapFromBranchDtoToBranch).toList();
            repo.branches().addAll(branchesFromGitHub);
            log.info("branches ssh included from repo " + repoName + " successfully");
        }
        return repoToReturn;
    }
}