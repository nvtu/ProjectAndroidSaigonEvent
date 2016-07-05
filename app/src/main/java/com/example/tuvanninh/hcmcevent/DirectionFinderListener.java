package com.example.tuvanninh.hcmcevent;

import java.util.List;

/**
 * Created by Tu Van Ninh on 03/07/2016.
 */
public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
