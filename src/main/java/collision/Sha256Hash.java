package collision;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Sha256Hash implements CryptoHash {

	private final MessageDigest sha;
	
	public Sha256Hash() {
		 try {
			  sha = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			  throw new IllegalStateException("SHA-256 algorithm not found", e);
		}
	}
	
	@Override
	public int length() {
		return 32;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		sha.reset();
		return sha.digest(source);
	}
	
}
