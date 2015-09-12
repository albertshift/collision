package collision;

public enum HashAlgorithm {

	HASHCODE(new ArrayHashCodeHash()),
	SHA1(new Sha1Hash()),
	SHA256(new Sha256Hash()), 
	RIPEMD128(new Ripemd128Hash()),
	RIPEMD160(new Ripemd160Hash()),
	
	MURMUR32(new Murmur32Hash()),
	MURMUR64(new Murmur64Hash()),
	MURMUR128(new Murmur128Hash());
	
	private CryptoHash instance;

	private HashAlgorithm(CryptoHash ins) {
		this.instance = ins;
	}

	public CryptoHash getInstance() {
		return instance;
	}
	
}
