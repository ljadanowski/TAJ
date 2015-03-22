package com.example.mockdemo.messenger;

public class MessageServiceImpl implements MessageService 
{
	@Override
	public ConnectionStatus checkConnection(String server) 
	{
		if(server.endsWith(".pl") && server.equals(server.toLowerCase()))
		{
			String nowy = server.substring(0, server.lastIndexOf('.'));
			if(nowy.isEmpty() || nowy.length() == 1 || nowy.charAt(nowy.length() - 1) == '.') 
				return ConnectionStatus.FAILURE;
			else
			{
				String zakazane = "@#$%^&*(){}[]?<>!+\\/\"";
				for(int i = 0; i<zakazane.length(); i++)
				{
					if(nowy.indexOf(zakazane.charAt(i)) != -1)
						return ConnectionStatus.FAILURE;
				}
				return ConnectionStatus.SUCCESS;
			}
		}
			
		else return ConnectionStatus.FAILURE;
	}

	@Override
	public SendingStatus send(String server, String message) throws MalformedRecipientException 
	{
		//adres serwera by³ przynajmniej 4 znakowy sprawdza juz metoda checkConnection()
		if(checkConnection(server) == ConnectionStatus.FAILURE || message.length() < 3) 
		//	return SendingStatus.SENDING_ERROR;
			throw new MalformedRecipientException();
		else return SendingStatus.SENT;
	}
	public static void main(String [] args)
	{
		String tekst = "Ala ma kota";
		System.out.println(tekst.contains("m"));
		
	}
}
