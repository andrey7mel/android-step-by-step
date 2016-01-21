package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.presenter.vo.Contributor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class RepoContributorsMapper implements Func1<List<ContributorDTO>, List<Contributor>> {

    @Inject
    public RepoContributorsMapper() {
    }

    @Override
    public List<Contributor> call(List<ContributorDTO> branchDTOs) {
        List<Contributor> contributors = Observable.from(branchDTOs)
                .map(contributorDTO -> new Contributor(contributorDTO.getLogin()))
                .toList()
                .toBlocking()
                .first();
        return contributors;
    }
}
