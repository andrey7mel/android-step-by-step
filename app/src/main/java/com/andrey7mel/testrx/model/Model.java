package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.data.Repo;

import java.util.List;

import rx.Observable;

public interface Model {

    Observable<List<Repo>> getRepoList(String name);
}
