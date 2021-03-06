# SimplerJackson
For a simpler usage pattern for Jackson

##### The power of Jackson and the flexibility of org.json

This is the simple target of this work.

The Jackson library for json parsing is doubtless the most versatile JSON library out there, but for most developers their 
day to day use cases for json consists of:

Building json,
Parsing values from a json response from wherever,
Encoding and Decoding whole classes to and from JSON

Jackson does all this well, but at a cost. Some of these tasks can be a bit complex or repetitive.

So SimplerJackson shamelessly marries the best of Jackson and the simplicity of org.json's syntax to get a quick, 
yet easy to use library.

We give you JSONObject and JSONArray for building json objects and arrays from the ground up and for parsing json strings.


We give you a Converter class also to make json encoding and decoding of whole java classes as simple as a method call within that class.




Here are simple examples:

```java
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
```

## Validating JSON structure against a standard schema
As of Sat-15-Aug 2020, we have added methods to allow Java API developers validate incoming json strings against standard Json schema defined as Java classes.


If say your standard json structure is:

```json
{
  "user":{
     "fname" : "Sam",
     "lname" : "Lane",
     "age" : 18,
     "height" : 1.92,

     "location" : {
         "lat" : 3.465,
         "lng" : 4.253
     }
     "creds" : {
         "password" : "amiol34xwr",
         "security_question" : "what is the name of your favorite world city?",
         "security_answer" : "lagos"
     }
  }
}
```

A general template to model this would be a Java class, say:


```java

public class User{

static class Location{

private double lat;
private double lng;

}


static class Creds{

private double password;
private String security_question;
private String security_answer;

}

private Creds creds;
private Location location;
private String fname;
private String lname;
private int age;
private double height;

}
```

Make sure that your Java class contains the necessary json fields that you wish to validate any incoming json request against.
The validator ignores static fields in the Java class and uses the object fields alone.
If the incoming json has a field that is not present in the proper arrangement according to the schema dictated by the java class,
it fails the validation.
Also, if the general data type of the json field's value is not same as specified in the Java class being used as the json schema,
it fails the validation also.

You may run the json validator in LENIENT or STRICT mode.

In STRICT mode, all fields present in the incoming json must be present in the target schema(Java class) and all fields present in the target schema must
also be present in the incoming json.

In LENIENT mode, all fields present in the incoming json must be present in the target schema, but not otherwise.
Also in LENIENT mode, the value of fields in JSONArrays and JSONobjects may be null, not so in STRICT mode.





