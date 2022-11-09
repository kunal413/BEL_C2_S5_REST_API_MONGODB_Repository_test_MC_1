package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.service;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    Track saveTrack(Track track);
    List<Track>getAllTrackData() throws Exception;
    boolean deleteTrack(int trackId) throws TrackNotFoundException;
    List<Track>findAllTrackByArtistName(String artistName)throws TrackNotFoundException;
    List<Track>findAllTrackWhereTrackRatingGrThen(int artistRating)throws TrackNotFoundException;
}
