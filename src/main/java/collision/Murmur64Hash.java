package collision;

import java.nio.ByteBuffer;

public final class Murmur64Hash implements CryptoHash {

	@Override
	public int length() {
		return 8;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		ByteBuffer bb = ByteBuffer.wrap(source);
		long hash = MurmurHash.hash2_64(bb, 0, bb.capacity(), 0);
		byte[] result = new byte[8];
		longToBytes(hash, result, 0);
		return result;
	}

	
	private static void longToBytes(long val, byte[] out, int offset) {
		int j = offset;
		out[j++] = (byte) (val);
		out[j++] = (byte) (val >> 8);
		out[j++] = (byte) (val >> 16);
		out[j++] = (byte) (val >> 24);
		out[j++] = (byte) (val >> 32);
		out[j++] = (byte) (val >> 40);
		out[j++] = (byte) (val >> 48);
		out[j++] = (byte) (val >> 56);
	}

}
