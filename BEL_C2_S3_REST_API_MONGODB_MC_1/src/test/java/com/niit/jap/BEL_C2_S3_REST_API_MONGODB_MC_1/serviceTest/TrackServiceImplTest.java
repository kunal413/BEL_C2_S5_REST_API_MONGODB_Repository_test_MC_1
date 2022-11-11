package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.serviceTest;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExitException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotFoundException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.repository.TrackRepository;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.service.TrackServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {
    List<Track> customerList;
    Artist artist1, artist2;
    @Mock
    private TrackRepository trackRepository;
    @InjectMocks
    private TrackServiceImpl trackService;
    private Track track1, track2;

    @BeforeEach
    void setUp() {
        artist1 = new Artist(1, "state1");
        track1 = new Track(1001, "Johny", 6, artist1);
        artist2 = new Artist(2, "state2");
        track2 = new Track(1002, "Harry", 4, artist2);
        customerList = Arrays.asList(track1, track2);
    }

    @AfterEach
    void tearDown() {
        track1 = null;
        track2 = null;
    }

    @Test
    public void givenTrackToSaveReturnSavedCustomerSuccess() throws TrackNotFoundException {
        //  when(trackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(any())).thenReturn(track1);
        assertEquals(track1, trackService.saveTrack(track1));
        verify(trackRepository, times(1)).save(any());
        //  verify(trackRepository, times(1)).findById(any());
    }

//    @Test
//    public void givenTrackToSaveReturnTrackFailure(){
//       when(trackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(track1));
//
//        assertThrows(TrackAlreadyExitException.class, () -> trackService.saveTrack(track1));
//        verify(trackRepository, times(0)).save(any());
//        verify(trackRepository, times(1)).findById(any());
//    }

    @Test
    public void givenTrackToDeleteShouldDeleteSuccess() throws TrackNotFoundException {
        when(trackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(track1));
        boolean flag = trackService.deleteTrack(track1.getTrackId());
        assertEquals(true, flag);

        verify(trackRepository, times(1)).deleteById(any());
        verify(trackRepository, times(1)).findById(any());
    }

}