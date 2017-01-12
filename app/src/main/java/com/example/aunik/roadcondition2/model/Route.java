package com.example.aunik.roadcondition2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aunik on 1/5/17.
 */
public class Route {

    private int id;

    public Route(int id, List<RoadSegment> roadSegments) {
        this.id = id;
        this.roadSegments = roadSegments;
    }

    private List<RoadSegment> roadSegments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RoadSegment> getRoadSegments() {
        return roadSegments;
    }

    public void setRoadSegments(List<RoadSegment> roadSegments) {
        this.roadSegments = roadSegments;
    }

    public static  Route getSampleRoute(){
        RoadSegment roadSegment1 = new RoadSegment("123", 1, null);
        RoadSegment roadSegment2 = new RoadSegment("123", 1, null);

        List<RoadSegment> roadSegments = new ArrayList<RoadSegment>();
        roadSegments.add(roadSegment1);
        roadSegments.add(roadSegment2);

        return new Route(555, roadSegments);

    }
}
