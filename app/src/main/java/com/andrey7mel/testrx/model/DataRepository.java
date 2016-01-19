package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.api.ApiModule;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataRepository implements IDataRepository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiModule.getApiInterface();

    public DataRepository() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()) // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
        ;
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface
                .getRepositories(name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<BranchDTO>> getRepoBranches(String owner, String name) {
        return apiInterface
                .getBranches(owner, name)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<ContributorDTO>> getRepoContributors(String owner, String name) {
        return apiInterface
                .getContributors(owner, name)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
