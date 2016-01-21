package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.presenter.vo.Branch;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class RepoBranchesMapper implements Func1<List<BranchDTO>, List<Branch>> {

    @Inject
    public RepoBranchesMapper() {
    }

    @Override
    public List<Branch> call(List<BranchDTO> branchDTOs) {
        List<Branch> branches = Observable.from(branchDTOs)
                .map(branchDTO -> new Branch(branchDTO.getName()))
                .toList()
                .toBlocking()
                .first();
        return branches;
    }
}
