import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Based upon most recent version of https://github.com/bitcoin/bitcoin/blob/master/src/bitcoin-util.cpp
public class BlockChainData {
    final long version;
    final BigInteger prevBlock;
    final BigInteger merkleRoot;
    final long timestamp;
    final long bits;
    final long nonce;
    final byte[] block = new byte[4 + 32 + 32 + 4 + 4 + 4];
    final BigInteger target;
    MessageDigest digest; // not thread-safe!

    public BlockChainData(long version, BigInteger prevBlock, BigInteger merkleRoot, long timestamp, long bits, long nonce) {
        this.version = version;
        this.prevBlock = prevBlock;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.bits = bits;
        this.nonce = nonce;

        target = BlockChainData.getTarget(bits);

        BlockChainData.encodeLittleEndian(BigInteger.valueOf(version).toByteArray(), block, 0, 4);
        BlockChainData.encodeLittleEndian(prevBlock.toByteArray(), block, 4, 32);
        BlockChainData.encodeLittleEndian(merkleRoot.toByteArray(), block, 4 + 32, 32);
        BlockChainData.encodeLittleEndian(BigInteger.valueOf(timestamp).toByteArray(), block, 4 + 32 + 32, 4);
        BlockChainData.encodeLittleEndian(BigInteger.valueOf(bits).toByteArray(), block, 4 + 32 + 32 + 4, 4);
        BlockChainData.encodeLittleEndian(BigInteger.valueOf(nonce).toByteArray(), block, 4 + 32 + 32 + 4 + 4, 4);
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public BlockChainData(BlockChainData orig) {
        this(orig.version, orig.prevBlock, orig.merkleRoot, orig.timestamp, orig.bits, orig.nonce);
    }

    public static void encodeLittleEndian(byte[] bytes, byte[] dest, int destPos, int length) {
        length = Math.min(length, bytes.length - (bytes[0] == 0 ? 1 : 0));
        for (int pos = bytes[0] == 0 ? 1 : 0, d = destPos + length - 1; d >= destPos; pos++, d--)
            dest[d] = bytes[pos];
    }

    public static byte[] reverseBytes(byte[] bytes) {
        for (int pos = 0, revPos = bytes.length - 1 - pos; pos < bytes.length / 2; pos++, revPos--) {
            byte temp = bytes[pos];
            bytes[pos] = bytes[revPos];
            bytes[revPos] = temp;
        }
        return bytes;
    }

    public static BigInteger getTarget(long bits) {
        int nSize = (int) (bits >> 24);
        BigInteger nWord = BigInteger.valueOf(bits & 0x007fffff);
        if (nSize <= 3)
            nWord = nWord.shiftRight(8 * (3 - nSize));
        else
            nWord = nWord.shiftLeft(8 * (nSize - 3));
        return nWord;
    }

    public boolean tryBitcoinHash(long nonce) {
        BlockChainData.encodeLittleEndian(BigInteger.valueOf(nonce).toByteArray(), block, 4 + 32 + 32 + 4 + 4, 4);
        return new BigInteger(1, reverseBytes(digest.digest(digest.digest(block)))).compareTo(target) <= 0;
    }

    // Genesis block:
    // https://blockchain.info/rawblock/000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f
    public final static BlockChainData initialBitcoinBlock = new BlockChainData(1L, BigInteger.valueOf(0),
            new BigInteger("4A5E1E4BAAB89F3A32518A88C31BC87F618F76673E2CC77AB2127B7AFDEDA33B", 16), 0x495FAB29L,
            0x1D00FFFFL, 0x7C2BAC1DL);

    // Block from Nov 04, 2023, 5:21:31 PM:
    // https://blockchain.info/rawblock/00000000000000000000644cfb03bf82cc9db58a7e8a9e6973bbf6e218a14f5e
    public final static BlockChainData block815296 = new BlockChainData(0x20006000,
            new BigInteger("00000000000000000003d78b6b9ad76154f8eeccd9ba02ad58d49f43028151b9", 16),
            new BigInteger("f059b12dc7beaee95f23e40feacb8beaceb8aed087c0e10a440523c3306a6d10", 16), 0x654670e5L,
            0x17048194L, 0xb61c10bdL);

    public static BlockChainData getDataForBlock815296() {
        return new BlockChainData(block815296);
    }
}
