package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.clientgithub.dto.BranchGitHubDto;
import com.example.appgithubrepo.domain.clientgithub.dto.OwnerGitHubDto;
import com.example.appgithubrepo.domain.clientgithub.dto.RepoFromGithubDto;
import com.example.appgithubrepo.domain.model.Branch;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.model.User;
import com.example.appgithubrepo.infrastructure.controller.dto.BranchDto;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.UserDto;

import java.util.List;

public class RepoMapper {

    public static ReposResponseDto mapFromRepoToReposResponseDto(Repo repo) {
        UserDto userDto = mapFromUserToUserDto(repo.user());
        List<BranchDto> branchDto = getBranchDtos(repo);
        return new ReposResponseDto(repo.name(), userDto, branchDto);
    }

    private static List<BranchDto> getBranchDtos(Repo repo) {
        return repo.branches().stream()
                .map(RepoMapper::mapFromBranchToBranchDto)
                .toList();
    }

    public static UserDto mapFromUserToUserDto(User user) {
        return new UserDto(user.login());
    }

    public static BranchDto mapFromBranchToBranchDto(Branch branch) {
        return new BranchDto(branch.name(), branch.lastCommitSha());
    }

    public static Repo mapFromReposResponseDtoToRepo(RepoFromGithubDto repoFromGithubDto) {
        String name = repoFromGithubDto.name();
        User user = mapFromUserDtoToUser(repoFromGithubDto.ownerGitHubDto());
        List<Branch> branches = mapFromBranchDtosToBranches(repoFromGithubDto.branches());
        return new Repo(name, user, branches);
    }

    private static List<Branch> mapFromBranchDtosToBranches(List<BranchGitHubDto> branches) {
        return branches.stream()
                .map(RepoMapper::mapFromBranchDtoToBranch)
                .toList();
    }

    private static Branch mapFromBranchDtoToBranch(BranchGitHubDto branchGitHubDto) {
        return new Branch(branchGitHubDto.name(), branchGitHubDto.lastCommitSha());
    }

    private static User mapFromUserDtoToUser(OwnerGitHubDto ownerGitHubDto) {
        return new User(ownerGitHubDto.login());
    }
}