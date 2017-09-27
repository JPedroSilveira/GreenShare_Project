package com.seedshare.uploadCare.api;

import com.seedshare.uploadCare.data.DataWrapper;
import com.seedshare.uploadCare.data.FileData;

public class FileDataWrapper implements DataWrapper<File, FileData> {

    private final Client client;

    public FileDataWrapper(Client client) {
        this.client = client;
    }

    public File wrap(FileData data) {
        return new File(client, data);
    }

}
