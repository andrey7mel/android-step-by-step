package com.andrey7mel.testrx.view.adapters;

import com.andrey7mel.testrx.presenter.vo.BranchVO;

import java.util.List;

public class BranchesAdapter extends BaseAdapter<BranchVO> {

    public BranchesAdapter(List<BranchVO> list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        String text = list.get(position).getName();
        holder.text.setText(text);
    }


}
