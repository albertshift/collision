package collision;

import org.spongycastle.crypto.digests.RIPEMD128Digest;

public final class Ripemd128Hash implements CryptoHash {

	private final RIPEMD128Digest digest = new RIPEMD128Digest();
	
	@Override
	public int length() {
		return 16;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		digest.reset();
    digest.update(source, 0, source.length);
    byte[]  result = new byte[16];
    digest.doFinal(result, 0);
		return result;
	}

}
