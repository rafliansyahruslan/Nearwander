package com.nearwander.application.nearwander.tabbed.Utility;

/**
 * Created by Rafli on 4/26/17.
 */

import java.io.Serializable;

public class GooglePlacesBean implements Serializable {
    private String description;
    private String placeId;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getPlaceId(){
        return placeId;
    }

    public void setPlaceId(String placeId){
        this.placeId = placeId;
    }
}

