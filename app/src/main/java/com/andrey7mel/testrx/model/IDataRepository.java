package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;

import java.util.List;

import rx.Observable;

public interface IDataRepository {

    Observable<List<RepositoryDTO>> getRepoList(String name);

    Observable<List<BranchDTO>> getRepoBranches(String owner, String name);

    Observable<List<ContributorDTO>> getRepoContributors(String owner, String name);

}
