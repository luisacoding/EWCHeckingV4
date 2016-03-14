package com.arun.app;

public class JsonConvert {
	public static void main(String[] args) {
        String res="{" + "\"total\"" +":1" + ","+ "\"rows\"" + ":" + "[" + "{" + "\"json\""+":"+"\"lxtian\"" + "}" +"]"+"}";
        String res2="{" + "\"total\"" +":1" + ","+ "\"rows\"" + ":" + "[" + "{" + "\"json\""+":"+"\"lxtian\"" + "}" +"]"+"}";
        StringBuilder json = new StringBuilder();
        String s="{" + "\"total\"" + ":2" + ","+"\"rows\"" +":" +"[";
        String s1="{" + "\"json\"" + ":" +"\"lxtian\"" + "}";
        String s2="{" + "\"json\"" + ":" +"\"lxtian2\"" + "}";
        String s3="]}";
        String comma=",";
        json.append(s);
        json.append(s1);
        json.append(comma);
        json.append(s2);
        json.append(s3);
        String jsonString="{\"user\":{\"id\":\"123\",\"name\":\"张三\",\"say\":\"Hello , i am a action to print a json!\",\"password\":\"JSON\"},\"success\":true}";
        
        System.out.println(json);
        System.out.println(json.length());
	}
}
