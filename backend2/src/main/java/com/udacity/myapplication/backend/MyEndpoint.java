/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.myapplication.backend;

import com.example.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.udacity.com",
                ownerName = "backend.myapplication.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that says joke
     */
    @ApiMethod(name = "sayJoke")
    public MyBean sayJoke() {
        MyBean response = new MyBean();
        Joker joker = new Joker();
        response.setData(joker.getJoke());

        return response;
    }

}
