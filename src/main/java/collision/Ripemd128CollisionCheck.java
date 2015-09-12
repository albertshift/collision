package collision;

import java.util.Arrays;

public class Ripemd128CollisionCheck {

	private static CryptoHash HASH = HashAlgorithm.RIPEMD128.getInstance();
	
	public static String hex(byte[] blob) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i != blob.length; ++i) {
			int val = blob[i] & 0xFF;
			str.append(Integer.toHexString(val)).append(", ");
		}
		return str.toString();
	}
	
	public static void main(String[] args) {
		
		//System.out.println(hex(HASH.hashIt("".getBytes())));
		
		long msg1[] = new long[] {
				
				0x579faf8e, 0x9ecf579, 0x574a6aba, 0x78413511, 0xa2b410a4, 0xad2f6c9f, 0xb56202c, 0x4d757911,
				0xbdeaae7, 0x78bc91f2, 0x47bc6d7d, 0x9abdd1b1, 0xa45d2015, 0x817104ff, 0x264758a8, 0x61064ea5
				
		};
		
		
		long msg2[] = new long[] {
				
				0x579faf8e, 0x9ecf579, 0x574a6aba, 0x78513511, 0xa2b410a4, 0xad2f6c9f, 0xb56202c, 0x4d757911,
				0xbdeaae7, 0x78bc91f2, 0xc7c06d7d, 0x9abdd1b1, 0xa45d2015, 0x817104ff, 0x264758a8, 0xe1064ea5
		
		};
		
		byte[] m1 = convert(msg1);
		byte[] m2 = convert(msg2);
		
		System.out.println("m1 = " + hex(m1));
		System.out.println("m2 = " + hex(m2));
		
		byte[] h1 = HASH.hashIt(m1);
		byte[] h2 = HASH.hashIt(m2);
		
		System.out.println("collision = " + Arrays.equals(h1, h2));
		System.out.println("h1 = " + hex(h1));
		System.out.println("h2 = " + hex(h2));
		
	}
	
	private static byte[] convert(long in[]) {
		
		byte[] result = new byte[64];
		int j = 0;
		
		for (int i = 0; i != in.length; ++i) {
			
			long val = in[i];
			
			result[j++] = (byte)(val & 0xFF);
			result[j++] = (byte)((val >> 8) & 0xFF);
			result[j++] = (byte)((val >> 16) & 0xFF);
			result[j++] = (byte)((val >> 24) & 0xFF);
			
			//result[j++] = (byte)((val >> 16) & 0xFF);
			//result[j++] = (byte)((val >> 8) & 0xFF);
			//result[j++] = (byte)(val & 0xFF);
			
		}
		
		return result;
	}
	
}
