package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.vo.Contributor;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

public class RepoContributorsMapperTest extends BaseTest {

    @Inject
    protected RepoContributorsMapper repoContributorsMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
    }

    @Test
    public void testCall() throws Exception {
        List<Contributor> contributorList = repoContributorsMapper.call(contributorDTOs);
        assertEquals(11, contributorList.size());

        assertEquals("hotchemi", contributorList.get(0).getName());
        assertEquals("mrmike", contributorList.get(1).getName());
        assertEquals("amitkot", contributorList.get(2).getName());
        assertEquals("maarekj", contributorList.get(10).getName());
    }
}