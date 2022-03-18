package com.company.pastebook;

import com.company.pastebook.models.Post;
import com.company.pastebook.services.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SearchTest {

    @Mock
    SearchService searchService;

    //searchUser Test
    @Test
    public void testSearchUser() {

        Mockito.when(searchService.searchUser("Pastebook")).thenReturn(new ResponseEntity("User found", HttpStatus.OK));
        ResponseEntity response = searchService.searchUser("Pastebook");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Mockito.verify(searchService).searchUser("Pastebook");
    }

}
