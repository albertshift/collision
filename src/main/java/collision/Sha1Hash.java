package collision;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Sha1Hash implements CryptoHash {

	private final MessageDigest sha;
	
	public Sha1Hash() {
		 try {
			  sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			  throw new IllegalStateException("SHA-1 algorithm not found", e);
		}
	}
	
	@Override
	public int length() {
		return 20;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		sha.reset();
		return sha.digest(source);
	}
	
}
