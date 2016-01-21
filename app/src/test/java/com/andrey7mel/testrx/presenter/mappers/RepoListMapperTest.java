package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.vo.Repository;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

public class RepoListMapperTest extends BaseTest {

    @Inject
    protected RepoListMapper repoListMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
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