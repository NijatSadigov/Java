public class Utils {
    // This is the actual solution value.
    // You may temporarily use it as the starting point to instantly "find the solution".
//  public static long NONCE_RANGE_START = 0xb61c_10bd;

    // This is near the last possible value of the nonce range.
    // You may temporarily use it as the starting point to instantly "fail the search".
//  public static long NONCE_RANGE_START = 0xFFFF_0000L;

    // This is a reasonable start.
    // The result will be found, but it will take a bit of work.
    public static long NONCE_RANGE_START = 0xb610_0000L;

    public static long NONCE_RANGE_END   = 0xFFFF_FFFFL;

    public static long STEPS_BETWEEN_CHECKS = 1 << 16;

    public static long SENTINEL = -1L;
    public static long NOT_FOUND_SOLUTION = -2L;

    public static void printHashRate(long currentNonce, long nanos) {
        long range = currentNonce - NONCE_RANGE_START;
        double hashRate = range * 1.0e9 / (double)nanos;
        System.out.printf("Hash rate: %d hashes per second%n", (int)hashRate);
    }

    public static void printSolution(long solution) {
        System.out.printf("Solved: 0x%x%n", (int)solution);
    }
}
