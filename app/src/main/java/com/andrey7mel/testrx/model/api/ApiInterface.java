package com.andrey7mel.testrx.model.api;

import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<RepositoryDTO>> getRepositories(@Path("user") String user);

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<ContributorDTO>> getContributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/branches")
    Observable<List<BranchDTO>> getBranches(@Path("owner") String owner, @Path("repo") String repo);

}
