package collision;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MurMur128CollisionTest {

	private static CryptoHash HASH = HashAlgorithm.MURMUR128.getInstance();
	
	public static void main(String[] args) {
		
		byte[] msg1 = new byte[] {92, 105, 113, -93, 0, 0, 0, 0, 66, 103, 90, 19, 0, 0, 0, 0};
		byte[] msg2 = new byte[] {-32, -111, 5, -94, 0, 0, 0, 0, -41, -121, -48, -49, 0, 0, 0, 0};
		
		long[] hash1 = MurmurHash.hash3_x64_128(ByteBuffer.wrap(msg1), 0, 16, 0);
		long[] hash2 = MurmurHash.hash3_x64_128(ByteBuffer.wrap(msg2), 0, 16, 0);
		
		System.out.println("hash1 = " + Arrays.toString(hash1));
		System.out.println("hash2 = " + Arrays.toString(hash2));
		
		System.out.println("h1 = " + Arrays.toString(HASH.hashIt(msg1)));
		System.out.println("h2 = " + Arrays.toString(HASH.hashIt(msg2)));
		
		System.out.println("Collision = " + Arrays.equals(hash1, hash2));
		
	}
	
}
