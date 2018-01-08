//import java.util.Random;

public class Day6 {

    class Bank implements Comparable<Bank> {

        private int id;
        private int blocks;

        public Bank(int id, int blocks) {
            this.id = id;
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return "ID " + id + "; Size: " + blocks + "\n";
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Bank) {
                return id == ((Bank) other).id;
            } else {
                return false;
            }
        }

        public int compareTo(Bank other) {
            if (this.blocks > other.blocks) {
                return 1;
            }
            if (this.blocks < other.blocks) {
                return -1;
            }

            return 0;
        }
    }

    private void run() {
        Bank[] banks = new Bank[4];
        //Random random = new Random();
        // Initialize the memory banks
        //for (int i = 0; i < banks.length; i++) {
        //    banks[i] = new Bank(i, random.nextInt(8));
        //}
        banks[0] = new Bank(0,0);
        banks[1] = new Bank(1,2);
        banks[2] = new Bank(2,7);
        banks[3] = new Bank(3,0);

        System.out.println("All memory banks initialised");
        System.out.println("Original memory banks: " + getBlocks(banks));
        System.out.println("Largest memory bank: " + largestBank(banks));
        String nextResult;
        int attempts = 0;
        System.out.println("Attempt " + ++attempts);
        String firstResult = rebalanceBank(banks);
        System.out.println(firstResult);
        do {
            System.out.println("Attempt " + ++attempts);
            nextResult = rebalanceBank(banks);
            System.out.println(nextResult);
        } while (!firstResult.equals(nextResult) && attempts < 5);
    }

    private Bank largestBank(Bank[] banks) {
        Bank largestBank = banks[0];
        for (int i = 0; i < banks.length; i++) {
            if (largestBank.compareTo(banks[i]) < 0 && largestBank.id < banks[i].id) {
                largestBank = banks[i];
            }
        }
        return largestBank;
    }

    private String rebalanceBank(Bank[] banks) {
        Bank largestBank = largestBank(banks);
        int pos = largestBank.id;
        int blocks = largestBank.blocks;
        largestBank.blocks = 0;
        do {
            for (int i = pos + 1; i < banks.length; i++) {
                if (blocks <= 0) break;
                banks[i].blocks++;
                blocks--;
            }
            for (int i = 0; i <= pos; i++) {
                if (blocks <= 0) break;
                banks[i].blocks++;
                blocks--;
            }
        } while (blocks > 0);

        return getBlocks(banks);
    }

    public String getBlocks(Bank[] banks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < banks.length; i++) {
            sb.append(banks[i].blocks);
            if (i < banks.length - 1)
                sb.append(",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.run();
    }

}