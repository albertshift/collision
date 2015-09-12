package collision;

import org.spongycastle.crypto.digests.RIPEMD160Digest;

public final class Ripemd160Hash implements CryptoHash {

	private final RIPEMD160Digest digest = new RIPEMD160Digest();
	
	@Override
	public int length() {
		return 20;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		digest.reset();
    digest.update(source, 0, source.length);
    byte[]  result = new byte[20];
    digest.doFinal(result, 0);
		return result;
	}

}
