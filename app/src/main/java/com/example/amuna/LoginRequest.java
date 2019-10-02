package com.example.amuna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    final static private String URL = "http://matehunter.cafe24.com/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String Email, String Password, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("Email",Email);
        parameters.put("Password",Password);
    }
    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}