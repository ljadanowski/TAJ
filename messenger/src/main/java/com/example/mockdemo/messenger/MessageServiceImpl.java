package com.example.mockdemo.messenger;

public class MessageServiceImpl implements MessageService 
{
	@Override
	public ConnectionStatus checkConnection(String server) 
	{
		
		/* Metoda u¿ywana do testowania po³¹czeñ do danego serwera. Zwraca statusy:
    	ConnectionStatus.SUCCES w przypadku powodzenia
    	ConnectionStatus.FAILURE w przypadku b³êdu. 
    	Wymagamy aby adres serwera by³ poprawnym adresem w uproszczonym formacie 
    	URL, np: wp.pl, inf.ug.edu.pl 
    	Uwaga: po³¹czenie koñczy siê sukcesem tylko dla adresów z poddomeny .pl	
    	*/
		//sprawdzanie czy napis sklada sie z samych lit
		return null;
	}

	@Override
	public SendingStatus send(String server, String message) throws MalformedRecipientException 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
