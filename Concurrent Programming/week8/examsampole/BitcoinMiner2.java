
public class BitcoinMiner2 {
    public static void main(String[] args) {
        //TODO: copy primary task here and make modifications based on description
        //TODO: in our pool, we use two more cores this time

        createHashCheckers(???);

        //TODO: additional task #1 in the pool (for queue submission), do the following:
            sendNonces(bq, isFound);
            BitcoinMiner.checkFoundSolution(tasks);
            //TODO: notify on isFound to make sure that the search ends, even in failure

        //TODO: additional task in the pool #2 (for queue termination), do the following:
            //TODO: wait for isFound to be notified

            isFound.set(true);
            BitcoinMiner.sendSentinels(???);

            // TODO: wait for additional task #1 to finish

        // TODO: wait for additional task #2 to finish

        BitcoinMiner.finishUp(???);
    }

    private static void createHashCheckers(???) {
        for (int i = 0; i < coreCount; i++) {
            createHashChecker(???);
        }
    }

    private static void createHashChecker(???) {
        //TODO CHANGE: if we found the correct nonce, notify isFound before returning it
    }

    private static void sendNonces(???) {
        //TODO CHANGE: this time, we don't check whether the solution is found here
        //TODO CHANGE: rather, we check at the beginning of each iteration if isFound is true
        //TODO CHANGE: if it is, we don't need to go on
    }
}
