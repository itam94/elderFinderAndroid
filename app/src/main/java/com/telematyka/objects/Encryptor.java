package com.telematyka.objects;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import android.util.Log;


public class Encryptor {
	
	private String key;
	private String initVector;
	
	public Encryptor(){
		this.key = "Bar12345Bar12345";
		this.initVector = "RandomInitVector";
	}
	
	private String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            Log.w("encrypted string: ",Base64.encodeToString(encrypted, 16) + "\n" + skeySpec.hashCode());

            return Base64.encodeToString(encrypted, 16);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
	
	public String encrypt(String value){
		return this.encrypt(this.key,this.initVector,value);
	}
	
	 public String decrypt(String key, String initVector, String encrypted) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	            	
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

	            byte[] original = cipher.doFinal(Base64.decode(encrypted,0));
						//DatatypeConverter.parseBase64Binary(encrypted)

	            return new String(original);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return null;
	    }
	 public String decrypt(String value){
			return this.decrypt(this.key,this.initVector,value);
		}
	
}
