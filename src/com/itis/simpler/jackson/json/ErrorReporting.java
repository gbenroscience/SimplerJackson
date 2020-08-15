/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itis.simpler.jackson.json;

/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class ErrorReporting {

    /**
     * In this mode, all the fields in the inspected json must be represented in
     * the target schema. If there are fields in the target schema that do not
     * appear in the inspected json, the inspected json is still given a pass.
     *
     * The target schema is a java class whose fields are the required fields of
     * a valid json required by the consumer.
     */
    public static final int LENIENT = 1;
    /**
     * Every field in the inspected json must exist in the target schema. The
     * target schema is a java class whose fields are the required fields of a
     * valid json required by the consumer.
     */
    public static final int STRICT = 2;

    public static int reportingMode = LENIENT;

    private final boolean valid;
    private final String missingKey;
    private final String message;

    public ErrorReporting(boolean valid, String missingKey, String message) {
        this.valid = valid;
        this.missingKey = missingKey;
        this.message = message;
    }

    public String getMissingKey() {
        return missingKey;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {

        JSONObject obj = new JSONObject();
        obj.put("valid", valid);
        obj.put("missingKey", missingKey);
        obj.put("message", message);

        return obj.toString();
    }

}
