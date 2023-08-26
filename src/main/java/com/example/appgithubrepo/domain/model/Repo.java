package com.example.appgithubrepo.domain.model;

import java.util.List;

public record Repo(String name, User user, List<Branch> branches) {
}