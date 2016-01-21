package com.andrey7mel.testrx.model;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.dto.BranchDTO;
import com.andrey7mel.testrx.model.dto.ContributorDTO;
import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.andrey7mel.testrx.other.TestConst;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class ModelImplTest extends BaseTest {


    @Inject
    ApiInterface apiInterface;

    @Inject
    Model model;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);
    }

    @Test
    public void testGetRepoList() {
        RepositoryDTO[] repositoryDTOs = testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class);

        when(apiInterface.getRepositories(TestConst.TEST_OWNER)).thenReturn(Observable.just(Arrays.asList(repositoryDTOs)));

        TestSubscriber<List<RepositoryDTO>> testSubscriber = new TestSubscriber<>();
        apiInterface.getRepositories(TestConst.TEST_OWNER).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<RepositoryDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(7, actual.size());
        assertEquals("Android-Rate", actual.get(0).getName());
        assertEquals("andrey7mel/Android-Rate", actual.get(0).getFullName());
        assertEquals(26314692, actual.get(0).getId());
    }

    @Test
    public void testGetRepoBranches() {

        BranchDTO[] branchDTOs = testUtils.getGson().fromJson(testUtils.readString("json/branches"), BranchDTO[].class);

        when(apiInterface.getBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO)).thenReturn(Observable.just(Arrays.asList(branchDTOs)));

        TestSubscriber<List<BranchDTO>> testSubscriber = new TestSubscriber<>();
        model.getRepoBranches(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<BranchDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(3, actual.size());
        assertEquals("QuickStart", actual.get(0).getName());
        assertEquals("94870e23f1cfafe7201bf82985b61188f650b245", actual.get(0).getCommit().getSha());

    }

    @Test
    public void testGetRepoContributors() {
        ContributorDTO[] contributorDTOs = testUtils.getGson().fromJson(testUtils.readString("json/contributors"), ContributorDTO[].class);

        when(apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO)).thenReturn(Observable.just(Arrays.asList(contributorDTOs)));


        TestSubscriber<List<ContributorDTO>> testSubscriber = new TestSubscriber<>();
        apiInterface.getContributors(TestConst.TEST_OWNER, TestConst.TEST_REPO).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

        List<ContributorDTO> actual = testSubscriber.getOnNextEvents().get(0);

        assertEquals(11, actual.size());
        assertEquals("hotchemi", actual.get(0).getLogin());
        assertEquals("User", actual.get(0).getType());
        assertEquals(471318, actual.get(0).getId());

    }
}