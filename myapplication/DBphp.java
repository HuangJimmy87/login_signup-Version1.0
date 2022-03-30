package com.example.myapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

public class DBphp {
    public static String DBstring(String i,String j,String Wcook,String url){

        String result = "抓不到";
        try{
            HttpClient hc = new DefaultHttpClient();
            HttpPost hp = new HttpPost(url);
            hp.addHeader("Cookie",Wcook+";expires=Thu,31-Dec-37 23:55:55 GMT;path=/");

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("un01",i));
            params.add(new BasicNameValuePair("up01",j));
            HttpEntity entity = new StringEntity("AAAA");
            hp.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse hr=hc.execute(hp);

            result = EntityUtils.toString(hr.getEntity(), HTTP.UTF_8);
        }catch (Exception e){
            result =e.toString();
        }
        return result;
    }
}
