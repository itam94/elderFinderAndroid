package com.telematyka.objects;


import java.io.Serializable;

public class SlaveProfile extends Slave{
    private String telephoneNumber;
    private String masterNameSurname;

    public void setMasterNameSurname(String nameSurname){
        this.masterNameSurname = nameSurname;
    }
    public String getMasterNameSurname(){
        return this.masterNameSurname;
    }
    public void setTelephoneNumber(String telephoneNumber){
        this.telephoneNumber = telephoneNumber;
    }
    public String getTelephoneNumber(){
        return this.telephoneNumber;
    }
}
