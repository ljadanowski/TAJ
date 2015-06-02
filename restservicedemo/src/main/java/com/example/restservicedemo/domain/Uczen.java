package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Uczen 
{
	private long id;
	
	private String nazwisko;	
	private int wiek;
	private long id_nauczyciel;
	
	public Uczen(long id, String nazwisko, int wiek, long id_nauczyciel) 
	{
		super();
		this.id = id;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.id_nauczyciel = id_nauczyciel;
	}
	
	public Uczen() 
	{
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

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public long getId_nauczyciel() {
		return id_nauczyciel;
	}

	public void setId_nauczyciel(long id_nauczyciel) {
		this.id_nauczyciel = id_nauczyciel;
	}
	
}
