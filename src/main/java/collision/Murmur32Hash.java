package collision;

import java.nio.ByteBuffer;

public final class Murmur32Hash implements CryptoHash {

	@Override
	public int length() {
		return 4;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		ByteBuffer bb = ByteBuffer.wrap(source);
		int val = MurmurHash.hash32(bb, 0, bb.capacity(), 0);
		byte[] out = new byte[4];
		int j = 0;
		out[j++] = (byte) (val);
		out[j++] = (byte) (val >> 8);
		out[j++] = (byte) (val >> 16);
		out[j++] = (byte) (val >> 24);
		return out;
	}

}
