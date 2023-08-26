package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.BranchDto;
import com.example.appgithubrepo.domain.clientgithub.dto.CommitDto;
import com.example.appgithubrepo.domain.clientgithub.dto.Owner;
import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.Branch;
import com.example.appgithubrepo.domain.model.Commit;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.model.User;
import com.example.appgithubrepo.infrastructure.controller.dto.BranchResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.UserResponseDto;

import java.util.Collections;
import java.util.List;

public class RepoMapper {

    public static ReposResponseDto mapFromRepoToReposResponseDto(Repo repo) {
        UserResponseDto userResponseDto = mapFromUserToUserDto(repo.user());
        List<BranchResponseDto> branchDto = mapFromRepoToBranchResponseDto(repo);
        return new ReposResponseDto(repo.name(), userResponseDto, branchDto);
    }

    public static UserResponseDto mapFromUserToUserDto(User user) {
        return new UserResponseDto(user.login());
    }

    private static List<BranchResponseDto> mapFromRepoToBranchResponseDto(Repo repo) {
        return repo.branches().stream()
                .map(RepoMapper::mapFromBranchToBranchDto)
                .toList();
    }

    public static BranchResponseDto mapFromBranchToBranchDto(Branch branch) {
        return new BranchResponseDto(branch.name(), branch.commit().get(0).sha());
    }

    public static Repo mapFromReposResponseDtoToRepo(RepoFromGithubDto repoFromGithubDto) {
        String name = repoFromGithubDto.name();
        User user = mapFromUserDtoToUser(repoFromGithubDto.owner());
        List<Branch> branches = repoFromGithubDto.branches().stream().map(RepoMapper::mapFromBranchDtoToBranch).toList();
        return new Repo(name, user, branches);
    }

    private static Branch mapFromBranchDtoToBranch(BranchDto branchDto) {
        if (branchDto == null) {
            return new Branch("", Collections.emptyList());
        }
        List<Commit> commitList = branchDto.commit().stream().map(RepoMapper::mapFromCommitDtoToCommit).toList();
        return new Branch(branchDto.name(), commitList);
    }

    private static Commit mapFromCommitDtoToCommit(CommitDto commitDto) {
        return new Commit(commitDto.sha());
    }

    private static User mapFromUserDtoToUser(Owner ownerDtoGitHub) {
        if (ownerDtoGitHub == null) {
            return new User("");
        }
        return new User(ownerDtoGitHub.login());
    }
}