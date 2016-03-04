package com.andrey7mel.stepbystep.presenter.mappers;

import com.andrey7mel.stepbystep.model.dto.RepositoryDTO;
import com.andrey7mel.stepbystep.other.BaseTest;
import com.andrey7mel.stepbystep.presenter.vo.Repository;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class RepoListMapperTest extends BaseTest {

    @Inject
    protected RepoListMapper repoListMapper;

    private List<RepositoryDTO> repositoryDTOs;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        repositoryDTOs = Arrays.asList(repositoryDTOArray);

    }

    @Test
    public void testCall() throws Exception {
        List<Repository> repositoryList = repoListMapper.call(repositoryDTOs);

        assertEquals(7, repositoryList.size());

        assertEquals("Android-Rate", repositoryList.get(0).getRepoName());
        assertEquals("andrey7mel", repositoryList.get(0).getOwnerName());

        assertEquals("utils", repositoryList.get(6).getRepoName());
        assertEquals("andrey7mel", repositoryList.get(6).getOwnerName());
    }
}