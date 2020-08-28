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
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
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
            return nod != null && nod.isTextual() ? nod.asText("") : "";
        }
        return "";
    }

    public String optString(String key, String def) {
        if (parseNode != null) {
            JsonNode nod = parseNode.get(key);
            return nod != null && nod.isTextual() ? nod.asText(def) : def;
        }
        return def;
    }

    public boolean has(String key) {
        if (parseNode != null) {
            return parseNode.has(key);
        }
        return false;
    }

    public HashSet<String> keySet() {
        HashSet<String> set = new HashSet<>();

        Iterator<String> it = parseNode.fieldNames();

        while (it.hasNext()) {
            set.add(it.next());
        }

        return set;
    }

    public static ErrorReporting validateAgainstClass(String json, Class clas, int reportingMode) throws JsonProcessingException {

        try {
            JSONObject obj = new JSONObject(json);
            return obj.validateAgainstClass(clas, reportingMode);
        } catch (JsonProcessingException ex) {
            throw ex;
        }
    }

    /**
     * Used to quickly validate FLAT JSON STRUCTURES ONLY.
     *
     * @param clas The model class that has the appropriate field structure.
     * @param reportingMode The strictness of the error detection reporting
     * between the inspected json and the targetv json. It is one of:
     * <ol>
     * <li>
     * <div>
     * <span>{@link ErrorReporting#LENIENT}</span>
     * <p>
     * In the lenient mode, the validator allows fields to have null values.
     * </p>
     * </div>
     * </li>
     * <li>
     * <div>
     * <span>{@link ErrorReporting#STRICT}</span>
     * <p>
     * In the lenient mode, the validator does not allow fields to have null
     * values.
     * </p>
     * </div>
     * </li>
     * </ol>
     *
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

        ArrayList<Field> fields = new ArrayList<>(Arrays.asList(clas.getDeclaredFields()));
        //filter static fields out of it.
        fields.removeIf((Field f) -> java.lang.reflect.Modifier.isStatic(f.getModifiers()));
        ArrayList<String> fieldNames = new ArrayList<>();

        for (Field f : fields) {
            fieldNames.add(f.getName());
        }

        try {
            Iterator<Map.Entry<String, JsonNode>> it = parseNode.fields();

            String key = null;
            JsonNode node = null;

            //field count must match in strict mode
            if (ErrorReporting.STRICT == reportingMode) {
                int sz = fields.size();
                //Config.logInfo("parseNode.size() = " + parseNode.size() + " , and fields.length = " + sz+" parseNode = "+parseNode.toPrettyString());
                if (parseNode.size() < sz) {
                    return new ErrorReporting(false, null, "Invalid JSON. Some fields in the input JSON are missing from the target schema, " + clas.getName());
                } else if (parseNode.size() > sz) {
                    return new ErrorReporting(false, null, "Invalid JSON. Some fields in the target schema[" + clas.getName() + "] are missing from the input JSON.");
                }
            }
            while (it.hasNext()) {
                try {
                    Map.Entry<String, JsonNode> jsonField = it.next();
                    key = jsonField.getKey();
                    node = jsonField.getValue();

                    if (fieldNames.indexOf(key) == -1) {
                        return new ErrorReporting(false, key, "The key, `" + key + "` is missing from the target schema: " + clas.getName());
                    }
                    if (!fieldsNullable && (node == null || node.isNull())) {
                        return new ErrorReporting(false, null, "The key, `" + key + "` is  associated with a null value. HINT[STRICT MODE SEEMS TO BE ENABLED]");
                    }

                    Field schemaField = clas.getDeclaredField(key);
                    schemaField.setAccessible(true);

                    Class c = schemaField.getType();

                    if (isPrimitive(schemaField)) {

                        switch (node.getNodeType()) {

                            case NUMBER:
                                if (!Long.class.equals(c) && !Integer.class.equals(c) && !Short.class.equals(c)
                                        && !Byte.class.equals(c)
                                        && !long.class.equals(c) && !int.class.equals(c) && !short.class.equals(c)
                                        && !byte.class.equals(c) && !Double.class.equals(c) && !double.class.equals(c)
                                        && !Float.class.equals(c) && !float.class.equals(c)
                                        && !Character.class.equals(c) && !char.class.equals(c)) {

                                    return new ErrorReporting(false, key, "The key, `" + key + "` has a wrong type association (NUMBER)  as relates to the target schema: " + c.getName());

                                }

                                break;
                            case STRING:
                                if (!String.class.equals(c)) {
                                    return new ErrorReporting(false, key, "The key, `" + key + "` has a wrong type association (STRING)  as relates to the target schema: " + c.getName());
                                }

                                break;
                            case BOOLEAN:
                                if (!Boolean.class.equals(c) && !boolean.class.equals(c)) {
                                    return new ErrorReporting(false, key, "The key, `" + key + "` has a wrong type association (BOOLEAN)  as relates to the target schema: " + c.getName());
                                }
                                break;

                            default:

                                break;

                        }
                    } else { //not primitive field

                        if (node instanceof ObjectNode) {
                            JSONObject childObj = new JSONObject();
                            childObj.parseNode = (ObjectNode) node;
                            ErrorReporting kidReporter = childObj.validateAgainstClass(c, reportingMode);
                            if (!kidReporter.isValid()) {
                                return kidReporter;
                            }
                        } else if (node instanceof ArrayNode) {
                            //Using a class as a schema enforces the restriction that all objects in json array are of same type, eh goodie goodie!!
                            JSONArray childObj = new JSONArray();
                            childObj.parseNode = (ArrayNode) node;

                            Class arrayType = schemaField.getType();//type of data in the schema relating to the json array

                            ErrorReporting kidReporter = childObj.validateAgainstClass(arrayType, reportingMode);
                            if (!kidReporter.isValid()) {
                                return kidReporter;
                            }
                        } else {
                            return new ErrorReporting(false, key, "The key, `" + key + "` represents an unparseable node-type. "
                                    + "It is neither an object node nor an array node. Target schema is: " + c.getName());
                        }
                    }

                } catch (NoSuchFieldException ex) {
                    return new ErrorReporting(false, key, "The key, `" + key + "` is missing from the target schema: " + clas.getName());
                } catch (SecurityException ex) {
                    Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorReporting(false, key, "The target schema could not be accessed due to security restrictions");
                } catch (Exception ex) {//JsonProcessingException
                    Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
                    return new ErrorReporting(false, key, "Some Invalid JSON DATA discovered while parsing your data");
                }
            }//end while loop

            return new ErrorReporting(true, null, "Incoming JSON matches the target schema: " + clas.getName());
        } catch (Exception e) {
            return new ErrorReporting(false, null, "Incoming JSON may have syntax errors. ");
        }

    }

    static final boolean isPrimitive(Object obj) {
        Class klazz = obj.getClass();
        return (klazz.isPrimitive() || obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Boolean
                || obj instanceof Long || obj instanceof Short || obj instanceof Byte);
    }

    static final boolean isPrimitive(Field f) {
        Class<?> clazz = f.getType();

        return Long.class.equals(clazz) || long.class.equals(clazz) || Integer.class.equals(clazz) || int.class.equals(clazz)
                || Short.class.equals(clazz) || short.class.equals(clazz) || Float.class.equals(clazz) || float.class.equals(clazz)
                || Byte.class.equals(clazz) || byte.class.equals(clazz) || Double.class.equals(clazz) || double.class.equals(clazz)
                || String.class.equals(clazz) || Character.class.equals(clazz) || char.class.equals(clazz)
                || Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    private static void writeInLogger(Object obj) {
        Class klazz = obj.getClass();
        if (klazz.isPrimitive() || obj instanceof String || obj instanceof Integer || obj instanceof Double || obj instanceof Boolean
                || obj instanceof Long || obj instanceof Short || obj instanceof Byte) {
            System.out.println(obj.toString());
        } else {
            try {
                for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(klazz).getPropertyDescriptors()) {
                    if (propertyDescriptor.getWriteMethod() == null) {
                        continue;
                    }
                    Method m = propertyDescriptor.getReadMethod();
                    if (m != null) {
                        Object object = m.invoke(obj);
                        if (object != null) {
                            writeInLogger(object);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
         return parseNode != null ? parseNode.toString(): null;
    }

    public String toCuteString() {
        return parseNode != null ? parseNode.toPrettyString() : null;
    }

}
