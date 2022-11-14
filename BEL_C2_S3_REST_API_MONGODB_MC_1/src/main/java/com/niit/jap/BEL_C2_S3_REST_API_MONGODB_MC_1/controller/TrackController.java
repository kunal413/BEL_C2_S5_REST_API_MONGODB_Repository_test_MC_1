package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.controller;

import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExitException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotFoundException;
import com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("track/api/")
public class TrackController {
    private TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }
    @PostMapping("/track")
    public ResponseEntity<?>insertTrack(@RequestBody Track track) throws TrackAlreadyExitException {
        Track track1 =trackService.saveTrack(track);
        return new ResponseEntity<>(track1, HttpStatus.CREATED);
    }
    @GetMapping("/tracks")
    public ResponseEntity<?>fetchAllTrack(){
        ResponseEntity responseEntity=null;
        try {
            responseEntity=new ResponseEntity<>(trackService.getAllTrackData(),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity =new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?>deleteTrackById(@PathVariable("trackId") int trackId)throws TrackNotFoundException{
        ResponseEntity responseEntity =null;
        try {
            trackService.deleteTrack(trackId);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("products/{artistName}")
    public ResponseEntity<?> getAllCustomerByArtistName(@PathVariable String artistName){
        ResponseEntity responseEntity=null;

        try{
            responseEntity = new ResponseEntity(trackService.findAllTrackByArtistName(artistName), HttpStatus.OK);

        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }
    @GetMapping("product/{trackRating}")
    public ResponseEntity<?>findAllTrackWhereTrackRating(@PathVariable String trackRating){
        ResponseEntity responseEntity = null;
        try {
            responseEntity =new ResponseEntity<>(trackService.findAllTrackWhereTrackRatingGrThen(Integer.parseInt(trackRating)),HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
