package com.seedshare.uploadCare.data;

import java.net.URI;
import java.util.List;

public interface PageData<T> {

    List<T> getResults();

    boolean hasMore();

    URI getNext();
}
