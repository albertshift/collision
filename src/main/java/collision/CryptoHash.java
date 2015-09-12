package collision;

public interface CryptoHash {

	int length();
	
	byte[] hashIt(byte[] source);
	
}
