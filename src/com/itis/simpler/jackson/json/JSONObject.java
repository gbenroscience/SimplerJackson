/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itis.simpler.jackson.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class JSONObject {

    ObjectNode parseNode;

    public JSONObject() {
        this.parseNode = JsonNodeFactory.instance.objectNode(); // initializing     
    }

    public JSONObject(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {

            this.parseNode = mapper.readValue(json, ObjectNode.class);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void put(String key, String value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, boolean value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, int value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, short value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, float value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, long value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, double value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, byte[] value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, BigInteger value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, BigDecimal value) {
        parseNode.put(key, value); // building
    }

    public void put(String key, Object[] value) {
        ArrayNode anode = parseNode.putArray(key);
        for (Object o : value) {
            anode.addPOJO(o); // building 
        }
    }

    public void put(String key, JSONObject value) {
        parseNode.set(key, value.parseNode);
    }

    public void put(String key, JSONArray value) {
        parseNode.set(key, value.parseNode);
    }

    public void put(String key, Object value) {
        parseNode.putPOJO(key, value);
    }

    public int optInt(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asInt(0) : 0;
        }
        return 0;
    }

    public int optInt(String key, int def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asInt(def) : def;
        }
        return def;
    }

    public long optLong(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asLong(0) : 0;
        }
        return 0;
    }

    public long optLong(String key, long def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asLong(def) : def;
        }
        return def;
    }

    public double optDouble(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asDouble(0) : 0;
        }
        return 0;
    }

    public double optDouble(String key, double def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asDouble(def) : def;
        }
        return def;
    }

    public boolean optBoolean(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asBoolean(false) : false;
        }
        return false;
    }

    public boolean optBoolean(String key, boolean def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null ? nod.asBoolean(def) : def;
        }
        return def;
    }

    public double optFloat(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null && nod.isFloat() ? nod.floatValue() : 0;
        }
        return 0;
    }

    public double optFloat(String key, float def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null && nod.isFloat() ? nod.floatValue() : def;
        }
        return def;
    }

    public short optShort(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null && nod.isShort() ? nod.shortValue() : 0;
        }
        return 0;
    }

    public short optShort(String key, short def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null && nod.isShort() ? nod.shortValue() : def;
        }
        return def;
    }

    public byte optByte(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);

            if (nod != null && nod.isShort()) {

                short val = (short) nod.asInt();
                if (val >= -128 && val <= 127) {
                    return (byte) val;
                }

            }
            return 0;

        }
        return 0;
    }

    public byte optByte(String key, byte def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);

            if (nod != null && nod.isShort()) {

                short val = (short) nod.asInt(def);
                if (val >= -128 && val <= 127) {
                    return (byte) val;
                }

            }
            return def;

        }
        return def;
    }

    public JSONObject optJSONObject(String key) {
        if (parseNode != null) {
            if (parseNode.has(key)) {
                ObjectNode nod = parseNode.with(key);

                JSONObject obj = new JSONObject();
                obj.parseNode = nod;

                return obj;
            }

        }
        return new JSONObject();
    }

    public JSONArray optJSONArray(String key) {
        if (parseNode != null) {
            if (parseNode.has(key)) {
                ArrayNode nod = parseNode.withArray(key);

                JSONArray obj = new JSONArray();
                if (nod != null) {
                    obj.parseNode = nod;
                    return obj;
                }
            }

        }
        return new JSONArray();
    }

    public String optString(String key) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return parseNode != null && nod.isTextual() ? nod.asText("") : "";
        }
        return "";
    }

    public boolean has(String key) {
        if (parseNode != null) {
            return parseNode.has(key);
        }
        return false;
    }

    public HashSet<String> keySet() {
        HashSet<String> set = new HashSet<String>();

        Iterator<String> it = parseNode.fieldNames();

        while (it.hasNext()) {
            set.add(it.next());
        }

        return set;
    }

    @Override
    public String toString() {
        return parseNode.toString();
    }

    public String toCuteString() {
        return parseNode.toPrettyString();
    }

}

