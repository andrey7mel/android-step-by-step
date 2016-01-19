package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.presenter.vo.ContributorVO;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class RepoContributorsMapper implements Func1<List<ContributorDTO>, List<ContributorVO>> {

    @Override
    public List<ContributorVO> call(List<ContributorDTO> branchDTOs) {
        List<ContributorVO> contributors = Observable.from(branchDTOs)
                .map(contributorDTO -> new ContributorVO(contributorDTO.getLogin()))
                .toList()
                .toBlocking()
                .first();
        return contributors;
    }
}
