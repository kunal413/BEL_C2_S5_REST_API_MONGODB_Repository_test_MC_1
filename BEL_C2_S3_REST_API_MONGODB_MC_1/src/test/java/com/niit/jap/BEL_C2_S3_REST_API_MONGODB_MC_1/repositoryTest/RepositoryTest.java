package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.repositoryTest;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
 class CustomerRepositoryTest {


    @Autowired
    private TrackRepository trackRepository;
    private Artist artist;
    private Track track;

    @BeforeEach
    void setUp() {
        artist = new Artist(101, "sonu nigam");
        track = new Track(1001, "Jonny", 5, artist);
    }

    @AfterEach
    void tearDown() {
        artist = null;
        track = null;
        trackRepository.deleteAll();

    }

    @Test
    @DisplayName("Test case for saving customer object")
    void givenCustomerToSaveShouldReturnCustomer() {
        trackRepository.save(track);
        Track track1 = trackRepository.findById(track.getTrackId()).get();
        assertNotNull(track1);
        assertEquals(track.getTrackId(),track1.getTrackId());
    }


    @Test
    @DisplayName("Test case for deleting customer object")
    public void givenTrackToDeleteShouldDeleteCustomer() {
        trackRepository.insert(track);
        Track track1 = trackRepository.findById(track.getTrackId()).get();
        trackRepository.delete(track1);
        assertEquals(Optional.empty(), trackRepository.findById(track.getTrackId()));

    }

    @Test
    @DisplayName("Test case for retrieving all the  customer object")
    public void givenTrackReturnGetAllTrack() {

        trackRepository.insert(track);
        Artist artist1 = new Artist(102, "Florida ");
        Track track1 = new Track(1002, "Harry", 3, artist1);
        trackRepository.insert(track1);

        List<Track> list = trackRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Harry", list.get(1).getTrackName());

    }
}
