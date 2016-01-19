package com.andrey7mel.testrx.view.adapters;

import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.vo.Repository;

import java.util.List;

public class RepoListAdapterNew extends BaseAdapter<Repository> {
    private RepoListPresenter presenter;


    public RepoListAdapterNew(List<Repository> list, RepoListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        Repository repo = list.get(i);
        viewHolder.text.setText(repo.getRepoName());
        viewHolder.text.setOnClickListener(v -> presenter.clickRepo(repo));
    }

    public void setRepoList(List<Repository> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
