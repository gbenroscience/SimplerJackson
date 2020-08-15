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
import com.fasterxml.jackson.databind.node.POJONode;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class JSONArray {

    protected ArrayNode parseNode;

    public JSONArray() {
        this.parseNode = JsonNodeFactory.instance.arrayNode(); // initializing     
    }

    public JSONArray(String json) throws JsonProcessingException {
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

    public void put(Object... values) {
        for (Object val : values) {
            parseNode.addPOJO(val);
        }
    }

    public void put(JSONObject val) {
        parseNode.add(val.parseNode);
    }

    public void put(JSONObject... values) {
        for (JSONObject val : values) {
            parseNode.add(val.parseNode);
        }
    }

    public void put(JSONArray val) {
        parseNode.add(val.parseNode);
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

    public String optString(int index, String def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return parseNode != null && nod.isTextual() ? nod.asText(def) : def;
        }
        return def;
    }

    public int optInt(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asInt(0) : 0;
        }
        return 0;
    }

    public int optInt(int index, int def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asInt(def) : def;
        }
        return def;
    }

    public long optLong(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asLong(0) : 0;
        }
        return 0;
    }

    public long optLong(int index, long def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asLong(def) : def;
        }
        return def;
    }

    public double optDouble(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asDouble(0) : 0;
        }
        return 0;
    }

    public double optDouble(int index, double def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asDouble(def) : def;
        }
        return def;
    }

    public boolean optBoolean(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asBoolean(false) : false;
        }
        return false;
    }

    public boolean optBoolean(int index, boolean def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null ? nod.asBoolean(def) : def;
        }
        return def;
    }

    public double optFloat(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isFloat() ? nod.floatValue() : 0;
        }
        return 0;
    }

    public double optFloat(int index, float def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isFloat() ? nod.floatValue() : def;
        }
        return def;
    }

    public short optShort(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isShort() ? nod.shortValue() : 0;
        }
        return 0;
    }

    public short optShort(int index, short def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);
            return nod != null && nod.isShort() ? nod.shortValue() : def;
        }
        return def;
    }

    public byte optByte(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);

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

    public byte optByte(int index, byte def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);

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

    public JSONObject optJSONObject(int index) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(index);

            if (nod != null) {
                if (nod.isObject()) {
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

            if (nod != null) {
                if (nod.isArray()) {
                    ArrayNode anode = (ArrayNode) nod;
                    JSONArray obj = new JSONArray();
                    obj.parseNode = anode;
                    return obj;
                }

            }

        }
        return new JSONArray();
    }

    /**
     * Used to quickly validate FLAT JSON STRUCTURES ONLY.
     *
     * @param clas The Java array type that models this json array e.g.
     * String[].class for a json array that contains just strings
     * @param reportingMode The strictness of the error detection reporting
     * between the inspected json and the targetv json. It is one of:
     * <ol>
     * <li>{@link ErrorReporting#LENIENT}</li>
     * <li>{@link ErrorReporting#STRICT}</li>
     * </ol>
     * In the lenient mode, the validator checks to ensure that all fields in
     * the incoming json are present in the target schema. In the strict mode,
     * the validator checks to ensure that all fields in the incoming json are
     * present in the target schema, and all fields in the target schema are
     * present in the inspected json.
     *
     * @return an {@link ErrorReporting} that has the
     * {@link ErrorReporting#valid} set to true and the
     * {@link ErrorReporting#missingKey} set to null if the flat structure
     * matches the class we are matching against.
     *
     * It returns an {@link ErrorReporting} that has the
     * {@link ErrorReporting#valid} set to false and the
     * {@link ErrorReporting#missingKey} set to the missing key if the flat
     * structure does not match the class we are matching against.
     *
     * Match here signifies that all fields in the incoming json must be in the
     * class we are comparing against.
     */
    public ErrorReporting validateAgainstClass(Class clas, int reportingMode) {
        boolean fieldsNullable = reportingMode == ErrorReporting.LENIENT;
        Class arrayType = clas.getComponentType();//type of data in the schema relating to the json array...e.g. String for String[] , int for int[]

        int len = parseNode.size();

        JsonNode begin = null;
        for (int i = 0; i < len; i++) {
            JsonNode nod = parseNode.get(i);
            
            begin = begin == null ? nod : begin;

            ErrorReporting kidReporter = null;

            if (nod != null && !nod.isNull()) {
                
           
                Class cl = nod.getClass();
                if (cl != null) {

                    if (nod instanceof ObjectNode) {
                        JSONObject cob = new JSONObject();
                        cob.parseNode = (ObjectNode) nod;
                        kidReporter = cob.validateAgainstClass(arrayType, reportingMode);
                    } else if (nod instanceof POJONode) {
                        try {
                            JSONObject cob = new JSONObject(nod.toString());
                            
                            kidReporter = cob.validateAgainstClass(arrayType, reportingMode);
                        } catch (JsonProcessingException ex) {
                           return new ErrorReporting(false, null, "Couldn't parse json input successfully.");
                        }
                    } else if (nod instanceof ArrayNode) {
                        JSONArray childObj = new JSONArray();
                        childObj.parseNode = (ArrayNode) nod;
                        kidReporter = childObj.validateAgainstClass(arrayType, reportingMode);
                    } else {

                        if (!cl.equals(begin.getClass())) {//all classes within a json array for a Java class must be same for it to conform to a class schema
                            //Config.logError("cl = " + cl + " , begin-class = " + begin.getClass());
                            return new ErrorReporting(false, null, "All items in JSON Array must have the same type: " + cl.getName() + " not same as " + begin.getClass().getName());
                        }

                    }
                    if (kidReporter != null && !kidReporter.isValid()) {
                        return kidReporter;
                    }

                }
            } else {
                if (!fieldsNullable) {
                    return new ErrorReporting(false, null, "Incoming JSON has a null field at index " + i + ". Object type is:" + arrayType + ". HINT[STRICT MODE SEEMS TO BE ENABLED]");
                }
            }

        }

        return new ErrorReporting(true, null, "Incoming JSON matches the target schema: " + clas.getName());
    }

    public int length() {
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


