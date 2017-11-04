package com.greenshare.utils;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
 
public class IntegrationTestUtil{
 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
 
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyInclusion(JsonInclude.Value.construct(Include.ALWAYS, Include.ALWAYS));
        try {
            byte[] response = mapper.writeValueAsBytes(object);
            return response;
        }catch(Exception ex) {
        	return null;
        }
    }
}