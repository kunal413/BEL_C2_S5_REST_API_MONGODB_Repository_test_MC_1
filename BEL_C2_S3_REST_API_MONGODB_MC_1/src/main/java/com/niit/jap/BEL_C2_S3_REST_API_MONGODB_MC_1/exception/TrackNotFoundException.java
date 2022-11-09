package com.niit.jap.BEL_C2_S3_REST_API_MONGODB_MC_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "trackArtist not found there is given in track")
public class TrackNotFoundException extends Exception{
}
