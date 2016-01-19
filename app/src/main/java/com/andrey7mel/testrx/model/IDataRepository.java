package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.data.Repo;
import com.andrey7mel.testrx.presenter.filters.RepoListFilter;

import java.util.List;

import rx.Observable;

public interface IDataRepository {

    Observable<List<Repo>> getRepoList(RepoListFilter filter);
}
