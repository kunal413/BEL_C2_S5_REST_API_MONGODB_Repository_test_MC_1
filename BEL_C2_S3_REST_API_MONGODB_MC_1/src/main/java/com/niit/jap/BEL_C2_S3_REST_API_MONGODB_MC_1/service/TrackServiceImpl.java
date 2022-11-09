package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.service;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotFoundException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTrackData() throws Exception {
        return trackRepository.findAll();
    }

    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        boolean flog=false;
        if (trackRepository.findById(trackId).isEmpty()){
            throw new TrackNotFoundException();
        }
        else {
            trackRepository.deleteById(trackId);
            return true;
        }
    }

    @Override
    public List<Track> findAllTrackByArtistName(String artistName) throws TrackNotFoundException {
        if (trackRepository.findAllTrackByArtistName(artistName).isEmpty()){
            throw new TrackNotFoundException();
        }
        return trackRepository.findAllTrackByArtistName(artistName);
    }

    @Override
    public List<Track> findAllTrackWhereTrackRatingGrThen(int trackRating) throws TrackNotFoundException {
        if (trackRepository.findAllTrackWhereTrackRatingGrThen(trackRating).isEmpty()) {
            throw new TrackNotFoundException();
        }
        return trackRepository.findAllTrackWhereTrackRatingGrThen(trackRating);
    }
}
