package collision;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PowEstimator {
	
	private static HashAlgorithm HASH = HashAlgorithm.MURMUR64;
	
	private static long hashCount = 0L;
	
	private static int USING_BITS = Math.min(64, HASH.getInstance().length() * 8);
	
	private static byte[] EMPTY_HASH = new byte[HASH.getInstance().length()];
	
	public static void main(String[] args) {
		
		System.out.println("PowEstimator usingBits=" + USING_BITS);
		

		byte[] source = doHash("Hello World".getBytes());

		System.out.println("source = " + Arrays.toString(source));
		
		byte[] x = source;
		byte[] y = x;
		
		long time0 = System.currentTimeMillis();
		
		int mode = USING_BITS;
		
		for (long i = 0; i != Long.MAX_VALUE; ++i) {
			
			x = calc(x, mode, source);
			
			y = calc(y, mode, source);
			y = calc(y, mode, source);

			if (Arrays.equals(x, y)) {

				long RAD = (i+1);
				byte[] checkPoint = x;
				System.out.println("RAD = " + RAD + ", checkPoint = " + Arrays.toString(x));
				
				x = checkPoint;
				y = source;
				byte[] prevY = null;
				byte[] prevX = null;
				long checkingLoop = 0;
				long RADIUS = -1;
				long TAIL = -1;
				
				byte[] prevLoopStarts = null;
				byte[] prevLoopEnds = null;
				byte[] loopStarts = null;
				byte[] loopEnds = null;
				
				for (long m = 0; m != Long.MAX_VALUE; ++m) {
					
					prevX = x;
					prevY = y;
					
					x = calc(x, mode, source);
					y = calc(y, mode, source);
					
					if (TAIL != -1) {
						checkingLoop++;
					}
					
					if (TAIL == -1 && Arrays.equals(y, x)) {
						
						prevLoopEnds = prevX;
						prevLoopStarts = prevY;
						loopStarts = x;
						loopEnds = x;
						
						TAIL = m + 1;
						System.out.println("TAIL = " + TAIL + ", x = " + Arrays.toString(x));
						System.out.println("prevLoopEnds=" + Arrays.toString(prevLoopEnds));
						System.out.println("prevLoopStarts=" + Arrays.toString(prevLoopStarts));
						
						break;
					}
				}
				
					
				System.out.println("prevLoopStarts = " + Arrays.toString(prevLoopStarts));
				System.out.println("loopStarts = " + Arrays.toString(loopStarts));
				System.out.println("prevLoopEnds = " + Arrays.toString(prevLoopEnds));
				System.out.println("loopEnds = " + Arrays.toString(loopEnds));
				

				System.out.println("fun1 = " + Arrays.toString(calc(prevLoopStarts, mode, source)));
				System.out.println("fun2 = " + Arrays.toString(calc(prevLoopEnds, mode, source)));

				x = source;
				for (long p = 0; p != TAIL; ++p) {
					x = calc(x, mode, source);
					
					if (Arrays.equals(x, prevLoopStarts)) {
						System.out.println("TAIL_HEAD = " + (p+1));
						break;
					}
				}
				
				
			  break;
			}
			
		}
		
		long time1 = System.currentTimeMillis();
		System.out.println("timeMls = " + (time1 - time0));
		
		System.out.println("hashCount = " + hashCount);
		
		long variations = (1L << mode);
		
		System.out.println("variations = " + variations);

		
	}

	
	private static MessageDigest sha;
	
	static {
	 try {
		  sha = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	}
	
	public static byte[] doHash(byte[] source) {
		hashCount++;
		return HASH.getInstance().hashIt(source);
	}
	
	public static byte[] calc(byte[] x, int mode, byte[] source) {
		
		byte[] result = doHash(x);
		
		return mode(result, mode, source);
	}
	
	public static byte[] mode(byte[] x, int mode, byte[] source) {
		
		int hashLen = HASH.getInstance().length();
		
		if (mode > hashLen * 8) {
			throw new IllegalArgumentException("invalid mode " + mode);
		}
		
		byte[] result = Arrays.copyOf(x, x.length);
		
		int bits = mode % 8;
		int fullBytes = mode / 8;
		int lastZeroIndex = hashLen - fullBytes;
		if (bits > 0) {
			lastZeroIndex--;
		}
		
		System.arraycopy(source, 0, result, 0, lastZeroIndex);

		if (bits > 0) {
			
			int b = result[lastZeroIndex];
			int s = source[lastZeroIndex];
			
			switch(bits) {
			case 7:
				b &= 0x7F;
				s &= 0x80;
				break;
			case 6:
				b &= 0x3F;
				s &= 0xC0;
				break;
			case 5:
				b &= 0x1F;
				s &= 0xE0;
				break;
			case 4:
				b &= 0x0F;
				s &= 0xF0;
				break;
			case 3:
				b &= 0x07;
				s &= 0xF8;
				break;
			case 2:
				b &= 0x03;
				s &= 0xFC;
				break;
			case 1:
				b &= 0x01;
				s &= 0xFE;
				break;
			}
			
			result[lastZeroIndex] = (byte) (b | s);
			
		}
		
		return result;
		
	}
	
}
