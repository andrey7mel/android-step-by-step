package com.andrey7mel.testrx.view.adapters;

import com.andrey7mel.testrx.presenter.vo.ContributorVO;

import java.util.List;

public class ContributorsAdapter extends BaseAdapter<ContributorVO> {

    public ContributorsAdapter(List<ContributorVO> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        String text = list.get(i).getName();
        viewHolder.text.setText(text);
    }
}
