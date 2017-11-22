package com.example.theappexperts.firstapidemo.services;

import com.example.theappexperts.firstapidemo.model.CakeModel;
import com.example.theappexperts.firstapidemo.util.constants.API_LIST;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by TheAppExperts on 22/11/2017.
 */

public interface RequestInterface {

    @GET(API_LIST.CAKE_LIST_API)
    Observable<List<CakeModel>> getCakesList();

}
