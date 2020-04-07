

class Ciphertext {
	
	private String message;
	
	private byte[] sign_in_bytes;
	

	
	void separate_message_and_sign(byte[] ciphertext, int option) {
		
		int index = 0;
		
		for(int i=0;i<ciphertext.length;i++)
		{
			if(ciphertext[i]== 36)
			{	
				index = i+1;
				break;
			}	
		}	
		byte message_in_bytes[] = new byte[index-1];
		for(int i=0;i<index-1;i++)
		{
			message_in_bytes[i] = ciphertext[i];
		}		
		
		if(option == 1)
			message = new String(message_in_bytes);
		else
			message = new String(message_in_bytes) + " ";
			
		int k=0;
		for(int i=index;i<ciphertext.length;i++)
		{
			k++;
		}
		
		sign_in_bytes = new byte[k];
		k = 0;
		for(int i=index;i<ciphertext.length;i++)
		{
		sign_in_bytes[k] = ciphertext[i];
			k++;
		}
	}
	
	String getMessage() {
		return message;
	}
	
	byte[] getSignInBytes() {
		return sign_in_bytes;
	}
	

}
