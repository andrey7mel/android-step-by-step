package com.andrey7mel.stepbystep.view.adapters;

import com.andrey7mel.stepbystep.presenter.vo.Contributor;

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
