/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itis.simpler.jackson.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.math.BigDecimal;
import java.math.BigInteger; 

/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class JSONArray {

   
    protected ArrayNode parseNode; 

    public JSONArray() {
        this.parseNode = JsonNodeFactory.instance.arrayNode(); // initializing     
    }

    public JSONArray(String json) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        
            this.parseNode = mapper.readValue(json, ArrayNode.class);
 
    }

    public void putByte(byte val) {
        parseNode.add(val);
    }

    public void putShort(short val) {
        parseNode.add(val);
    }

    public void put(int val) {
        parseNode.add(val);
    }

    public void put(long val) {
        parseNode.add(val);
    }

    public void put(float val) {
        parseNode.add(val);
    }

    public void put(double val) {
        parseNode.add(val);
    }

    public void put(String val) {
        parseNode.add(val);
    }

    public void put(byte[] val) {
        parseNode.add(val);
    }

    public void put(BigDecimal val) {
        parseNode.add(val);
    }

    public void put(BigInteger val) {
        parseNode.add(val);
    }

    public void put(Object val) {
        parseNode.addPOJO(val);
    }
    
     public void put(int index, JSONArray value) {
        parseNode.set(index, value.parseNode);
    }
     
      public void put(int index, JSONObject value) {
        parseNode.set(index, value.parseNode);
    }
    
     public String optString(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asText("") : "";
        }
        return "";
    }
 public int optInt(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asInt(0) : 0;
        }
        return 0;
    }

    public long optLong(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asLong(0) : 0;
        }
        return 0;
    }

    public double optDouble(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asDouble(0) : 0;
        }
        return 0;
    }

    public boolean optBoolean(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asBoolean(false) : false;
        }
        return false;
    }

    public double optFloat(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isFloat() ? nod.floatValue() : 0;
        }
        return 0;
    }

    public short optShort(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isShort() ? nod.shortValue() : 0;
        }
        return 0;
    }

    public byte optByte(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);

            return nod != null && nod.isShort() ? (byte) nod.asInt(0) : 0;

        }
        return 0;
    }

    public JSONObject optJSONObject(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            
            if(nod != null){
                if(nod.isObject()){
                        ObjectNode obn = (ObjectNode) nod;
                          JSONObject obj = new JSONObject();
                          obj.parseNode = obn;
                          return obj;
                   }
                
            }

        }
        return new JSONObject();
    }
    
      public JSONArray optJSONArray(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            
            if(nod != null){
                if(nod.isArray()){
                        ArrayNode anode = (ArrayNode) nod;
                          JSONArray obj = new JSONArray();
                          obj.parseNode = anode;
                          return obj;
                   }
                
            }

        }
        return new JSONArray();
    }
      

    public int length(){
        return parseNode.size();
    }
         @Override
    public String toString() {
        return parseNode.toString();
    }
      public String toCuteString() {
        return parseNode.toPrettyString();
    }
    

}
