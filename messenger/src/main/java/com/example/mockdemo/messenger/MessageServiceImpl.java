package com.example.mockdemo.messenger;

public class MessageServiceImpl implements MessageService 
{
	@Override
	public ConnectionStatus checkConnection(String server) 
	{
		
		/* Metoda u�ywana do testowania po��cze� do danego serwera. Zwraca statusy:
    	ConnectionStatus.SUCCES w przypadku powodzenia
    	ConnectionStatus.FAILURE w przypadku b��du. 
    	Wymagamy aby adres serwera by� poprawnym adresem w uproszczonym formacie 
    	URL, np: wp.pl, inf.ug.edu.pl 
    	Uwaga: po��czenie ko�czy si� sukcesem tylko dla adres�w z poddomeny .pl	
    	*/
		//sprawdzanie czy napis sklada sie z samych lit
		return null;
	}

	@Override
	public SendingStatus send(String server, String message) throws MalformedRecipientException 
	{
		/* Metoda u�ywana do wysy�ania komunikat�w do serwera.
	     Wymagamy aby adres serwera by� przynajmniej 4 znakowy a komunikat
	     przynajmniej 3 znakowy, w przeciwnym wypadku metoda wyrzuca
	     MalformedRecipientException.
	     Zwraca statusy:
	     SendingStatus.SENT w przypadku powodzenia operacji
	     SendingStatus.SENDING_ERROR w przypadku b��du (przerwane po��czenie itp.
	     niezale�ne b��dy)
	     */
		return null;
	}

}
