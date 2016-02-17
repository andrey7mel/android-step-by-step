package com.andrey7mel.stepbystep.model;

import com.andrey7mel.stepbystep.model.dto.BranchDTO;
import com.andrey7mel.stepbystep.model.dto.ContributorDTO;
import com.andrey7mel.stepbystep.model.dto.RepositoryDTO;

import java.util.List;

import rx.Observable;

public interface Model {

    Observable<List<RepositoryDTO>> getRepoList(String name);

    Observable<List<BranchDTO>> getRepoBranches(String owner, String name);

    Observable<List<ContributorDTO>> getRepoContributors(String owner, String name);

}
