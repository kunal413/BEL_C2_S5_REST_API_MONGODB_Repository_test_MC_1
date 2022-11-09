package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.repository;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, Integer> {
    @Query("{'trackArtist.artistName':{$in : [?0]}}")
    List<Track>findAllTrackByArtistName(String artistName);
    @Query("{'trackRating':{$gt:4}}")
    List<Track>findAllTrackWhereTrackRatingGrThen(int trackRating);
}
