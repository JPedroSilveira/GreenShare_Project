package com.seedshare.uploadCare.upload;

import com.seedshare.uploadCare.api.File;

public interface Uploader {

    com.seedshare.uploadCare.api.File upload() throws UploadFailureException;

    Uploader store(boolean store);
}
