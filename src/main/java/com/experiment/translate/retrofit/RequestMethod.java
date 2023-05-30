package com.experiment.translate.retrofit;

import com.experiment.translate.connect.MCall;

public abstract class RequestMethod {
    public abstract MCall invoke(Object[] args);
}
