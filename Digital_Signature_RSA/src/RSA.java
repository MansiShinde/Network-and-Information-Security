
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
 
import java.io.*;
 
public class RSA {
 
	private BigInteger p;
 
	private BigInteger q;
 
	private BigInteger N;
 
	private BigInteger phi;
 
	private BigInteger e;
 
	private BigInteger d;
 
	private int bitlength = 1024;
 
	private int blocksize = 256;
 
	//blocksize in byte
 
	private Random r;
 
	public RSA() {
 
		r = new Random();
 
		p = BigInteger.probablePrime(bitlength, r);
 
		q = BigInteger.probablePrime(bitlength, r);
 
		N = p.multiply(q);
 
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
 
		e = BigInteger.probablePrime(bitlength/2, r);
 
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {
 
			e.add(BigInteger.ONE);
 
		}
 
		d = e.modInverse(phi);
 
	}
 
	public RSA(BigInteger e, BigInteger d, BigInteger N) {
 
		this.e = e;
 
		this.d = d;
 
		this.N = N;
 
	}
 
	@SuppressWarnings("deprecation")
	public static void main (String[] args) throws IOException {
 
		RSA rsa = new RSA();
 
		DataInputStream in=new DataInputStream(System.in);
 
		String teststring = in.readLine();
 
		System.out.println("Enter the plain text:");
 
 
		System.out.println("Encrypting String: " + teststring);
 
		System.out.println("String in Bytes: " + (teststring.getBytes()));
 
		String MessageDigest = createMessageDigest(teststring);
		System.out.println("Message Digest before encryption:"+MessageDigest);
		// encrypt
 
		byte[] encrypted = rsa.encrypt(MessageDigest.getBytes());
 
		System.out.println("Digital Signature in bytes:"+encrypted);
		System.out.println("Digital Signature in string:"+encrypted.toString());
		String enc = encrypted.toString();
		System.out.println("Digital Signature in bytes:"+enc.getBytes());
		
		System.out.println("$ in bytes:"+Arrays.toString(" ".getBytes()));
		
		System.out.println("Encrypted String in Bytes (Digital Signature): " + encrypted);
 
		// decrypt
 
		byte[] decrypted = rsa.decrypt(encrypted);
 
		System.out.println("Decrypted String in Bytes: " +  decrypted.toString());
 
		System.out.println("Decrypted String: " + new String(decrypted));
 
	}
 
	private static String bytesToString(byte[] encrypted) {
 
		String test = "";
 
		for (byte b : encrypted) {
 
			test += Byte.toString(b);
 
		}
 
		return test;
 
	}
 
	//Encrypt message
 
	public byte[] encrypt(byte[] message) {
 
		return (new BigInteger(message)).modPow(e, N).toByteArray();
 
	}
 
	// Decrypt message
 
	public byte[] decrypt(byte[] message) {
 
		return (new BigInteger(message)).modPow(d, N).toByteArray();
 
	}
 
	public static String createMessageDigest(String message) {
		 
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

	
}
