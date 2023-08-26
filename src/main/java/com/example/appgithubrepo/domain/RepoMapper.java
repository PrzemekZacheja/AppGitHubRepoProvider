package com.example.appgithubrepo.domain;

import com.example.appgithubrepo.domain.model.Branch;
import com.example.appgithubrepo.domain.model.Repo;
import com.example.appgithubrepo.domain.model.User;
import com.example.appgithubrepo.infrastructure.controller.dto.BranchDto;
import com.example.appgithubrepo.infrastructure.controller.dto.ReposResponseDto;
import com.example.appgithubrepo.infrastructure.controller.dto.UserDto;

import java.util.List;

public class RepoMapper {
    public static ReposResponseDto mapFromRepoToGetAllReposResponseDto(Repo repo) {
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
}