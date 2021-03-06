package model;

import java.sql.Timestamp;

import static tools.RandomString.getAlphaNumericString;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class Walk {

    private String walkId;

    private User user;

    /* Pressing button (starting the walk) */
    private Timestamp startTimestamp;
    private double[] startLocation; // latlong

    /* Releasing button */
    private Timestamp endTimestamp;
    private double[] endLocation; // latlong
    private boolean safe;


    public Walk(User user, Timestamp start_timestamp, double[] start_location){
        this.walkId = getAlphaNumericString(8);
        this.user = user;
        this.startTimestamp = start_timestamp;
        this.startLocation = start_location;
    }

    public void endWalk(Timestamp end_timestamp, double[] end_location, boolean safe){
        this.endTimestamp = end_timestamp;
        this.endLocation = end_location;
        this.safe = safe;
    }

}
