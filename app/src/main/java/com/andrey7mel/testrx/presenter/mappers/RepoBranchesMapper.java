package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.presenter.vo.BranchVO;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class RepoBranchesMapper implements Func1<List<BranchDTO>, List<BranchVO>> {

    @Override
    public List<BranchVO> call(List<BranchDTO> branchDTOs) {
        List<BranchVO> branches = Observable.from(branchDTOs)
                .map(branchDTO -> new BranchVO(branchDTO.getName()))
                .toList()
                .toBlocking()
                .first();
        return branches;
    }
}
