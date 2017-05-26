package com.telematyka.objects;
import java.util.List;

public class User {

	private String login;
	private String password;
	private String mail;
	private String nameSurname;
	private String telephoneNumber;
	private List<Slave> slaves;

	public User(){
		this.login ="";
		this.password ="";
		this.mail ="";
		this.telephoneNumber ="";
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public String getNameSurname() {
		return this.nameSurname;
	}
	
	public String getMail() {
		return this.mail;
	}

	public List<Slave> getSlaves() {
		return this.slaves;
	}

	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setSlaves(List<Slave> slaves) {
		this.slaves = slaves;
	}

	public void addSlave(Slave s) {
		this.slaves.add(s);
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}
