package com.example.appgithubrepo.domain.model;

import java.util.List;

public record Branch(String name, List<Commit> commit) {
}