package com.andrey7mel.testrx.presenter;

import com.andrey7mel.testrx.presenter.filters.RepoListFilter;

public interface IPresenter {
    void loadData();

    void setFilter(RepoListFilter filter);

    void unsubscribe();
}
