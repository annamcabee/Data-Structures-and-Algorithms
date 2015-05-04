import java.util.Random;

/**
 * Generates Heads or Tails on each flip.
 *
 * @version 1.0
 */
public class CoinFlipper {
    private Random gen;
    private int numFlips;

    /**
     * Construct a coin flipper object.
     */
    public CoinFlipper() {
        this(new Random());
    }

    /**
     *
     * Construct a coin flipper object with a given source of randomness.
     * @param gen source of randomness
     */
    public CoinFlipper(Random gen) {
        this.gen = gen;
    }

    /**
     * Simulate a random coin flip.
     * @return the result of the coin flip
     */
    public Coin flipCoin() {
        numFlips++;
        if (gen.nextBoolean()) {
            return Coin.HEADS;
        } else {
            return Coin.TAILS;
        }
    }

    /**
     * Gets the number of times the coin has been flipped.
     * @return the number of times it's been flipped
     */
    public int getNumFlips() {
        return numFlips;
    }

    /**
     *
     * A coin enum with a heads and tails value
     *
     */
    public static enum Coin {
        HEADS, TAILS
    }
}
