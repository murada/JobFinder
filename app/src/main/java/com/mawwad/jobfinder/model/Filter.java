package com.mawwad.jobfinder.model;

import java.io.Serializable;
import java.util.List;

public class Filter extends SearchUrlParameterKeys implements Serializable {

    private Provider provider ;


    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
