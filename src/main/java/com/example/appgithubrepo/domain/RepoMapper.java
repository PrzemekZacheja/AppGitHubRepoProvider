package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.BranchDto;
import com.example.appgithubrepo.domain.clientgithub.dto.CommitDto;
import com.example.appgithubrepo.domain.clientgithub.dto.Owner;
import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.*;
import com.example.appgithubrepo.infrastructure.controller.dto.BranchResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

public class RepoMapper {

    public static ReposResponseDto mapFromRepoToReposResponseDto(Repo repo) {
        UserResponseDto userResponseDto = mapFromUserToUserDto(repo.user());
        List<BranchResponseDto> branchesDto = repo.branches()
                                                  .stream()
                                                  .map(RepoMapper::mapFromBranchToBranchDto)
                                                  .toList();
        return new ReposResponseDto(repo.name(), userResponseDto, branchesDto);
    }

    public static UserResponseDto mapFromUserToUserDto(User user) {
        return new UserResponseDto(user.login());
    }

    public static BranchResponseDto mapFromBranchToBranchDto(Branch branch) {
        return new BranchResponseDto(branch.name(), branch.commit()
                                                          .sha());
    }

    public static Repo mapFromRepoFromGitHubToRepo(RepoFromGithubDto repoFromGithubDto) {
        String name = repoFromGithubDto.name();
        User user = mapFromOwnerDtoToUser(repoFromGithubDto.owner());
        if (repoFromGithubDto.branches() == null) {
            return new Repo(name, user, new ArrayList<>());
        }
        List<Branch> branches = repoFromGithubDto.branches()
                                                 .stream()
                                                 .map(RepoMapper::mapFromBranchDtoToBranch)
                                                 .toList();
        return new Repo(name, user, branches);
    }

    private static User mapFromOwnerDtoToUser(Owner ownerDtoGitHub) {
        if (ownerDtoGitHub == null) {
            return new User("");
        }
        return new User(ownerDtoGitHub.login());
    }

    public static Branch mapFromBranchDtoToBranch(BranchDto branchDto) {
        if (branchDto == null) {
            return new Branch("", new Commit(""));
        }
        Commit commit = mapFromCommitDtoToCommit(branchDto.commit());
        return new Branch(branchDto.name(), commit);
    }

    private static Commit mapFromCommitDtoToCommit(CommitDto commitDto) {
        return new Commit(commitDto.sha());
    }

    public static List<SimpleRepo> mapFromReposToSimpleRepos(List<Repo> repos) {
        return repos.stream()
                    .map(RepoMapper::mapFromRepoToSimpleRepo)
                    .toList();
    }

    private static SimpleRepo mapFromRepoToSimpleRepo(Repo repo) {
        return new SimpleRepo(repo.user()
                                  .login(), repo.name());
    }
}