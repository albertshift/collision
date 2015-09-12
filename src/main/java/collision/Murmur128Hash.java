package collision;

import java.nio.ByteBuffer;
import java.util.Arrays;

public final class Murmur128Hash implements CryptoHash {

	@Override
	public int length() {
		return 16;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		ByteBuffer bb = ByteBuffer.wrap(source);
		long[] hash = MurmurHash.hash3_x64_128(bb, 0, bb.capacity(), 0);
		byte[] result = new byte[16];
		longToBytes(hash[0], result, 0);
		longToBytes(hash[1], result, 8);
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
