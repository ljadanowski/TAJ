package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Nauczyciel 
{
	private long id;

	private String nazwisko; // dla ulatwienia samo nazwisko
	private String zawod;
	private long id_uczen;
	
	public Nauczyciel() {
	}

	public Nauczyciel(String nazwisko, String zawod, long id_uczen) 
	{
		this.nazwisko = nazwisko;
		this.zawod = zawod;
		this.id_uczen = id_uczen;
	}
	
	public Nauczyciel(long id, String nazwisko, String zawod, long id_uczen) 
	{
		super();
		this.id = id;
		this.nazwisko = nazwisko;
		this.zawod = zawod;
		this.id_uczen = id_uczen;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getZawod() {
		return zawod;
	}

	public void setZawod(String zawod) {
		this.zawod = zawod;
	}

	public long getId_uczen() {
		return id_uczen;
	}

	public void setId_uczen(long id_uczen) {
		this.id_uczen = id_uczen;
	}

}
