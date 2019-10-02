package com.example.amuna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://matehunter.cafe24.com/Register1.php";
    private Map<String, String> parameters;

    public RegisterRequest(String Email, String Password, String Name, int Gender, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("Email",Email);
        parameters.put("Password",Password);
        parameters.put("Name",Name);
        parameters.put("Gender",String.valueOf(Gender));
//
    }
    @Override
    public Map<String,String> getParams(){
        return parameters;
    }
}
