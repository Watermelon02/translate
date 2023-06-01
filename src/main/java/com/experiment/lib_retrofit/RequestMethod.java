package com.experiment.lib_retrofit;

import com.experiment.lib_connect.Call;

public abstract class RequestMethod {
    public abstract Call invoke(Object[] args);
}
