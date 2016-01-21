package com.andrey7mel.testrx.other;

import com.andrey7mel.testrx.BuildConfig;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.di.TestComponent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;


/**
 * Created by an.melnikov on 17.09.2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestApplication.class,
        constants = BuildConfig.class,
        sdk = 21)
@Ignore
public class BaseTest extends Assert {

    public TestComponent component;
    public TestUtils testUtils = new TestUtils();
    protected List<RepositoryDTO> repositoryDTOs;
    protected List<ContributorDTO> contributorDTOs;
    protected List<BranchDTO> branchDTOs;

    @Before
    public void setUp() throws Exception {
        component = (TestComponent) App.getComponent();

        RepositoryDTO[] repositoryDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);
        ContributorDTO[] contributorDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/contributors"), ContributorDTO[].class);
        BranchDTO[] branchDTOArray = testUtils.getGson().fromJson(testUtils.readString("json/branches"), BranchDTO[].class);

        repositoryDTOs = Arrays.asList(repositoryDTOArray);
        contributorDTOs = Arrays.asList(contributorDTOArray);
        branchDTOs = Arrays.asList(branchDTOArray);
    }

}
