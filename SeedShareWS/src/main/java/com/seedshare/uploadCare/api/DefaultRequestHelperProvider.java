package com.seedshare.uploadCare.api;

class DefaultRequestHelperProvider implements RequestHelperProvider {

    public RequestHelper get(Client client) {
        return new RequestHelper(client);
    }

}
