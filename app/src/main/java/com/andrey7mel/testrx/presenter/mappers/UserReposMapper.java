package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.presenter.vo.RepositoryVO;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class UserReposMapper implements Func1<List<RepositoryDTO>, List<RepositoryVO>> {
    @Override
    public List<RepositoryVO> call(List<RepositoryDTO> repositoryDTOs) {
        List<RepositoryVO> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new RepositoryVO(repoDTO.getName(), repoDTO.getOwner().getLogin()))
                .toList()
                .toBlocking()
                .first();
        return repoList;
    }

}
