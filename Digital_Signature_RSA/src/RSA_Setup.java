import java.math.BigInteger;
import java.util.Random;

class RSA_Setup {
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger phi;
	private BigInteger e;
	private BigInteger d;
	private int bitlength = 1024;
	
	private Random r;
	
	RSA_Setup() {
		r = new Random();
		p =BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		n = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(bitlength/2, r);
		d= e.modInverse(phi); 
	}
	
	byte[] encrypt(byte[] messageDigestInBytes)
	{
		return (new BigInteger(messageDigestInBytes)).modPow(d, n).toByteArray();
	}
	
	BigInteger getPublicKey() {
		return e;
	}
	
	BigInteger get_n() {
		return n;
	}
}

