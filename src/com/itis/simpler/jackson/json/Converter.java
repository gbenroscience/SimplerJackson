/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itis.simpler.jackson.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
  

/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class Converter<T> {
    // Serialize/deserialize helpers

    private Class clazz;

    public Converter() {}

    public T fromJsonString(String json , Class clazz) throws IOException {
        this.clazz = clazz;
        return getObjectReader().readValue(json);
    }

    public T fromJsonString(JsonNode json, Class clazz) throws IOException {
        if (json == null || json.isEmpty()) {
            return null;
        }
        this.clazz = clazz;
        return getObjectReader().readValue(json);
    }
  
    public String toJsonString(T obj) throws JsonProcessingException {
        this.clazz = obj.getClass();
        return getObjectWriter().writeValueAsString(obj);
    }

    private ObjectReader requestReader;
    private ObjectWriter requestWriter;

    private void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        

        requestReader = mapper.readerFor(clazz);
        requestWriter = mapper.writerFor(clazz);
    }

    private ObjectReader getObjectReader() {
        if (requestReader == null) {
            instantiateMapper();
        }
        return requestReader;
    }

    private ObjectWriter getObjectWriter() {
        if (requestWriter == null) {
            instantiateMapper();
        }
        return requestWriter;
    }

     

     

}
