package com.andrey7mel.testrx.view.adapters;

import com.andrey7mel.testrx.presenter.vo.Contributor;

import java.util.List;

public class ContributorsAdapter extends BaseAdapter<Contributor> {

    public ContributorsAdapter(List<Contributor> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        String text = list.get(i).getName();
        viewHolder.text.setText(text);
    }
}
