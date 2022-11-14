package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExitException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.service.TrackServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TrackServiceImpl trackService;

    @InjectMocks
    private TrackController trackController;
    private Track track1, track2;
    private Artist artist1 , artist2;
    List<Track> customerList;

    @BeforeEach
    void setUp() {
        artist1 = new Artist(102,"state1");
        track1 = new Track(1001,"Johny",4,artist1);
       artist2 = new Artist(122,"state2");
        track2 = new Track(1002,"Harry",8,artist2);
        customerList = Arrays.asList(track1,track2);

        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
    }

    @AfterEach
    void tearDown() {
        track1=null;
        track2 = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomer() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track1);
        mockMvc.perform(post("/track/api/track")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track1)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).saveTrack(any());

    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomerFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExitException.class);
        mockMvc.perform(post("/track/api/track")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(track1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).saveTrack(any());

    }
    @Test
    public void givenCustomerIdDeleteCustomer() throws Exception {
        when(trackService.deleteTrack(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/track/api/track/1001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService,times(1)).deleteTrack(anyInt());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }

}
