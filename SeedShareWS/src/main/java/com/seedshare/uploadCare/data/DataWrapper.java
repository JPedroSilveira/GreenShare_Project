package com.seedshare.uploadCare.data;

public interface DataWrapper<T, U> {

    T wrap(U data);

}
