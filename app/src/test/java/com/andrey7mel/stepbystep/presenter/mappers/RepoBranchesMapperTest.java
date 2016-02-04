package com.andrey7mel.stepbystep.presenter.mappers;

import com.andrey7mel.stepbystep.model.dto.BranchDTO;
import com.andrey7mel.stepbystep.other.BaseTest;
import com.andrey7mel.stepbystep.presenter.vo.Branch;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class RepoBranchesMapperTest extends BaseTest {

    @Inject
    protected RepoBranchesMapper branchesMapper;

    private List<BranchDTO> branchDTOs;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
        BranchDTO[] branchDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/branches"), BranchDTO[].class);
        branchDTOs = Arrays.asList(branchDTOArray);

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