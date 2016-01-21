package com.andrey7mel.testrx.presenter.mappers;

import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.presenter.vo.Branch;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

public class RepoBranchesMapperTest extends BaseTest {

    @Inject
    protected RepoBranchesMapper branchesMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
    }

    @Test
    public void testCall() throws Exception {
        List<Branch> branchList = branchesMapper.call(branchDTOs);

        assertEquals(3, branchList.size());
        assertEquals("QuickStart", branchList.get(0).getName());
        assertEquals("gh-pages", branchList.get(1).getName());
        assertEquals("master", branchList.get(2).getName());
    }
}