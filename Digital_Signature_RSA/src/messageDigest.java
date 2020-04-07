import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class messageDigest {

	String createMessageDigest(String message) {
		 
		 try {
			 
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 
			 byte[] messageDigest = md.digest(message.getBytes());
			 
			 BigInteger no = new BigInteger(1, messageDigest);
			 
			 String hashtext = no.toString(16); 
	         while (hashtext.length() < 32) { 
	             hashtext = "0" + hashtext; 
	         } 
	         return hashtext;
			 
		 }
		 catch(NoSuchAlgorithmException e) {
			 throw new RuntimeException(e); 
		 }
	 }
	
	boolean verification(String md1, String md2)
	{
		if(md1.equals(md2))
			return true;
		return false;
	}
	
}
