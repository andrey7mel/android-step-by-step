package com.andrey7mel.testrx.view.adapters;

import com.andrey7mel.testrx.presenter.RepoListPresenter;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

public class RepoListAdapterNew extends BaseAdapter<RepositoryVO> {
    private RepoListPresenter presenter;


    public RepoListAdapterNew(List<RepositoryVO> list, RepoListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        RepositoryVO repo = list.get(i);
        viewHolder.text.setText(repo.getRepoName());
        viewHolder.text.setOnClickListener(v -> presenter.clickRepo(repo));
    }

    public void setRepoList(List<RepositoryVO> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
