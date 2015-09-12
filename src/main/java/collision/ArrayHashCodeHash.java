package collision;

import java.util.Arrays;

public final class ArrayHashCodeHash implements CryptoHash {

	@Override
	public int length() {
		return 4;
	}

	@Override
	public byte[] hashIt(byte[] source) {
		int val = Arrays.hashCode(source);
		byte[] out = new byte[4];
		int j = 0;
		out[j++] = (byte) (val);
		out[j++] = (byte) (val >> 8);
		out[j++] = (byte) (val >> 16);
		out[j++] = (byte) (val >> 24);
		return out;
	}

}
