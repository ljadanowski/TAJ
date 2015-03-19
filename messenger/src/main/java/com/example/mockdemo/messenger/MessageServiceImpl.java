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
		/* Metoda u¿ywana do wysy³ania komunikatów do serwera.
	     Wymagamy aby adres serwera by³ przynajmniej 4 znakowy a komunikat
	     przynajmniej 3 znakowy, w przeciwnym wypadku metoda wyrzuca
	     MalformedRecipientException.
	     Zwraca statusy:
	     SendingStatus.SENT w przypadku powodzenia operacji
	     SendingStatus.SENDING_ERROR w przypadku b³êdu (przerwane po³¹czenie itp.
	     niezale¿ne b³êdy)
	     */
		return null;
	}

}
