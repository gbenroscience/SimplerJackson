/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itis.simpler.jackson.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GBEMIRO JIBOYE <gbenroscience@gmail.com>
 */
public class SimplerJacksonTest {
    
    
    static class Credentials {

        private String userName;
        private String uid;
        private String password;
        private long createdAt;

        public Credentials() {
        }

        public Credentials(String userName, String uid, String password, long createdAt) {
            this.userName = userName;
            this.uid = uid;
            this.password = password;
            this.createdAt = createdAt;
        }

        @JsonProperty("userName")
        public String getUserName() {
            return userName;
        }

        @JsonProperty("userName")
        public void setUserName(String userName) {
            this.userName = userName;
        }

        @JsonProperty("uid")
        public String getUid() {
            return uid;
        }

        @JsonProperty("uid")
        public void setUid(String uid) {
            this.uid = uid;
        }

        @JsonProperty("password")
        public String getPassword() {
            return password;
        }

        @JsonProperty("password")
        public void setPassword(String password) {
            this.password = password;
        }

        @JsonProperty("createdAt")
        public long getCreatedAt() {
            return createdAt;
        }

        @JsonProperty("createdAt")
        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public String encode() {
            try {
                return new Converter<Credentials>().toJsonString(this);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        public Credentials decode(String jsonData) {
            try {
                return new Converter<Credentials>().fromJsonString(jsonData, Credentials.class);
            } catch (Exception ex) {
                Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    }
    
    
    public static JSONObject testJSONObjectBuild() {
        JSONObject obj = new JSONObject();

        Credentials cred = new Credentials("Adesina", "01eab26bwkwjbak2vngxh9y3q6", "xxxxxx1234", System.currentTimeMillis());
        String arr[] = new String[]{"Boy", "Girl", "Man", "Woman"};
        int nums[] = new int[]{0, 1, 2, 3, 4, 5};

        obj.put("creds", cred);
        obj.put("pronouns", arr);
        obj.put("creds", cred);
        obj.put("nums", nums);

        System.out.println("json-coding: " + obj.toCuteString());

        return obj;
    }

    public static void testJSONObjectParse(String json) {
        JSONObject obj;
        try {
            obj = new JSONObject(json);

            JSONObject credsObj = obj.optJSONObject("creds");

            String userName = credsObj.optString("userName");
            String uid = credsObj.optString("uid");
            String password = credsObj.optString("password");
            long createdAt = credsObj.optLong("createdAt");

          
            System.out.println("<<---Parse Results--->>");
            System.out.println("userName = " + userName);
            System.out.println("uid = " + uid);
            System.out.println("password = " + password);
            System.out.println("createdAt = " + createdAt);


        } catch (JsonProcessingException ex) {
            Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
       public static JSONArray testJSONArrayBuild() {
        
         JSONArray array = new JSONArray();
        array.put(new Credentials("Lawani", "001uadywdbs", "ampouehehu", System.currentTimeMillis()));

        array.put("12");
        array.put(98);
        array.put(Math.PI);
        array.put("Good scores!");
        
        System.out.println("See the built array: "+array.toCuteString());

        return array;

    }
       
        
       public static void testJSONArrayParse(String json) {
        
        try {
            JSONArray array = new JSONArray(json);
            
            
            JSONObject credsObj = array.optJSONObject(0);
            
            //Parse credentials in index 0
            
            String userName = credsObj.optString("userName");
            String uid = credsObj.optString("uid");
            String password = credsObj.optString("password");
            long createdAt = credsObj.optLong("createdAt");

          
            //Now return to the  main array and parse other entries
            
            String twelve = array.optString(1);
            int ninety = array.optInt(2);
            double pi = array.optDouble(3);
            String scoreNews = array.optString(4);
            
            
            System.out.println("Parse Results");
            System.out.println("userName = " + userName);
            System.out.println("uid = " + uid);
            System.out.println("password = " + password);
            System.out.println("createdAt = " + createdAt);
            
             
            System.out.println("Parse Results");
            System.out.println("index 1 = " + twelve);
            System.out.println("index 2 = " + ninety);
            System.out.println("index 3 = " + pi);
            System.out.println("index 4 = " + scoreNews);
            
         
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
       
       public static String testCredentialsEncode(){
            Credentials cred = new Credentials("Adesina", "01eab26bwkwjbak2vngxh9y3q6", "xxxxxx1234", System.currentTimeMillis());
            
            String encoded = cred.encode();
            System.out.println("encoded credentials = "+encoded);
            return encoded;
       }
       
        public static Credentials testCredentialsDecode(String json){
            Credentials cred = new Credentials().decode(json);
            
            
            System.out.println("encoded credentials = "+cred.encode());
            
            
            return cred;
       }


    public static void main(String[] args) {
        
       JSONObject jo = testJSONObjectBuild();
        testJSONObjectParse(jo.toString());
        
        JSONArray ja = testJSONArrayBuild();
        
        testJSONArrayParse(ja.toString());
        
        String credsJSON = testCredentialsEncode();
        
        testCredentialsDecode(credsJSON);

    }
    
}
