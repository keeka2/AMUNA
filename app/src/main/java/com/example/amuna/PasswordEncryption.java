package com.example.amuna;

import java.security.MessageDigest;

public class PasswordEncryption {

    public PasswordEncryption() {
    }

    public String encrypt(String planText) {

        try{
        /*
         1. SHA-256으로 해시
             - MessageDigest객체 생성시 알고리즘을 "SHA-256"으로 해서 만든다.
                            해시된 데이터는 바이트 배열타입의 바이너리 데이터(이진 데이터)이다.
        */
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(planText.getBytes());
            byte byteData[] = md.digest();


        /*
         2. 해시된 데이터는 바이트 배열타입의 바이너리 데이터(이진 데이터)이므로
                          이것을 16진수 문자열(String)타입으로 변환해준다.
        */
            StringBuffer sb = new StringBuffer();

            for(int i=0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexsb = new StringBuffer();

            for (int i=0; i<byteData.length;i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1){
                    hexsb.append('0');
                }
                hexsb.append(hex);
            }

            return hexsb.toString();

        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }// end of encrypt(String planText)------------------------
}
