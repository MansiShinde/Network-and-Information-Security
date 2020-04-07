import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class DS_RSA_Alice {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

try {
	
			System.out.println("\n******* ALICE *******");
			
			String serverName = "localhost";
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int port = 8088;
			
			System.out.println("Enter the Message to send:");
			String Message = input.readLine();
			
			messageDigest md = new messageDigest();
			String MessageDigest = md.createMessageDigest(Message); //message digest created using MD5
			System.out.println("Message Digest is:"+MessageDigest);
			
			
			RSA_Setup rsa = new RSA_Setup();
				
			System.out.println("\nGenerating Digital Signature on Message Digest using Alice's public key...");
			byte[] DigitalSignature = rsa.encrypt(MessageDigest.getBytes()); //encrypting message digest using Alice's private key (d)
			
			System.out.println("Digital Signature is:"+ DigitalSignature.toString());
			
			System.out.println("\nCreating CiperText.....");
			
		      ByteArrayOutputStream concatenate = new ByteArrayOutputStream();
		      concatenate.write(Message.getBytes());
		      concatenate.write("$".getBytes()); 
		      concatenate.write(DigitalSignature);
		      
			byte[] ciphertext = concatenate.toByteArray();
		      
			System.out.println("Ciphertext is:"+ Arrays.toString(ciphertext));
			
			System.out.println("Which case to choose?\n 1. Verification Success case \n. 2.Verification Failed Case");
		      int option = Integer.parseInt(input.readLine()); 
		    	  
			
			System.out.println("\n\nConnecting to "+serverName + "on port "+port);
			Socket client = new Socket(serverName, port);
			
			System.out.println("\nJust connected to "+client.getRemoteSocketAddress());
			
			OutputStream outToServer = client.getOutputStream();
			
			ObjectOutputStream out = new ObjectOutputStream(outToServer); 
			DataOutputStream byteout = new DataOutputStream(outToServer);
			
			out.writeObject(rsa.get_n());
			out.writeObject(rsa.getPublicKey());
			byteout.writeInt(option);
			byteout.writeInt(ciphertext.length);
			byteout.write(ciphertext);
			
		
			
		//scan.close();	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
