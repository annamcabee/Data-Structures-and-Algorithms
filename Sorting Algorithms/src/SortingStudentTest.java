import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SortingStudentTest {
    private Zombie[] horde;
    private Zombie[] hordeByName;
    private Zombie[] hordeByKills;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        horde = new Zombie[10];
        horde[0] = new Zombie("Albert", 0, 7);
        horde[1] = new Zombie("Emily", 1, 1);
        horde[2] = new Zombie("Hemen", 0, 0);
        horde[3] = new Zombie("Kush", 4, 2);
        horde[4] = new Zombie("OZ", 3, 2);
        horde[5] = new Zombie("OZ", 4, 2);
        horde[6] = new Zombie("Saikrishna", 999, 2);
        horde[7] = new Zombie("Jonathan", 0, 2);
        horde[8] = new Zombie("Monica", 400, 58);
        horde[9] = new Zombie("David Smith", 1, 180);
        hordeByName = new Zombie[10];
        hordeByName[0] = horde[0];
        hordeByName[1] = horde[9];
        hordeByName[2] = horde[1];
        hordeByName[3] = horde[2];
        hordeByName[4] = horde[7];
        hordeByName[5] = horde[3];
        hordeByName[6] = horde[8];
        hordeByName[7] = horde[4];
        hordeByName[8] = horde[5];
        hordeByName[9] = horde[6];

        hordeByKills = new Zombie[10];
        hordeByKills[0] = horde[0];
        hordeByKills[1] = horde[2];
        hordeByKills[2] = horde[7];
        hordeByKills[3] = horde[1];
        hordeByKills[4] = horde[9];
        hordeByKills[5] = horde[4];
        hordeByKills[6] = horde[3];
        hordeByKills[7] = horde[5];
        hordeByKills[8] = horde[8];
        hordeByKills[9] = horde[6];

        }
        @Test(timeout = TIMEOUT)
        public void testBubblesort () {
           // Sorting.bubblesort(horde, (new Zombie(null, 0, 0)).getNameComparator());
           // assertArrayEquals(hordeByName, horde);
            Sorting.bubblesort(horde, (new Zombie(null, 0, 0)).getKillsComparator());
            assertArrayEquals(hordeByKills, horde);

        }

        @Test(timeout = TIMEOUT)
        public void testInsertionsort () {
          //  Sorting.insertionsort(horde, (new Zombie(null, 0, 0))
            //        .getNameComparator());
           // assertArrayEquals(hordeByName, horde);
            SortingForTest s = new SortingForTest();
            ArrayList<Integer> ints = new ArrayList<>();
            ints.add(100);
            ints.add(10);
            ints.add(0);
            ints.add(5);
            ints.add(1000);
            ints.add(4);
            ints.add(15);

            ArrayList<Integer> sortedInts = s.mergeSort(ints);
            for (int a : sortedInts) {
                System.out.print(a + " ");
            }
           // assertArrayEquals(hordeByKills, horde);
        }

        @Test(timeout = TIMEOUT)
        public void testShellsort () {
            Sorting.shellsort(horde, (new Zombie(null, 0, 0)).getNameComparator());
            for (int i = 1; i < horde.length; i++) {
                assertTrue(horde[i].getName().compareTo(horde[i - 1].getName())
                        >= 0);
            }
        }

        @Test(timeout = TIMEOUT)
        public void testQuicksort () {
            Sorting.quicksort(horde, (new Zombie(null, 0, 0)).getNameComparator(),
                    new Random(0x600dc0de));
            for (int i = 1; i < horde.length; i++) {
                assertTrue(horde[i].getName().compareTo(horde[i - 1].getName())
                        >= 0);
            }
        }

        @Test(timeout = TIMEOUT)
        public void testMergesort () {
            SortingForTest s = new SortingForTest();
            ArrayList<Integer> a = new ArrayList<>();
            a.add(10);
            a.add(1);
            a.add(40);
            a.add(3);
            a.add(15);
            a.add(20);
            ArrayList<Integer> arr = s.mergeSort(a);
            System.out.println(arr);
        }

        @Test(timeout = TIMEOUT)
        public void testRadixsort () {
            int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, 85, 3};
            int[] sortedArray = new int[]{3, 20, 28, 54, 58, 84, 85, 122};
            Random rand = new Random();
            int[] ints = new int[50];

            for (int i = 0; i < 50; i++) {
                ints[i] = rand.nextInt(100) + 20;
            }
            Sorting.radixsort(unsortedArray);
            assertArrayEquals(sortedArray, Sorting.radixsort(unsortedArray));
            Sorting.radixsort(ints);
        }
        @Test(timeout = 5000)
        public void testAll() {
            int[] ints = new int[7];
            ints[0] = 10;
            ints[1] = -3;
            ints[2] = 15;
            ints[3] = -45;
            ints[4] = 100;
            ints[5] = 60;
            ints[6] = -20;
            Sorting.radixsort(ints);
            for (int a : ints) {
                System.out.print(a + ", ");
            }
        }
        /**
         * This is a Zombie class that may or may not be related to Humans versus
         * Zombies (hvz.gatech.edu).
         * However it might just be a nondescript zombie. This isn't an
         * advertisement for the best game on campus.
         */
        private class Zombie {
            private String name;
            private int kills;
            private int daysSinceDeath;

            /**
             * Create a zombie.
             *
             * @param name name of zombie
             * @param kills number of kills by the zombie
             * @param daysSinceDeath number of days since the zombie died
             */
            public Zombie(String name, int kills, int daysSinceDeath) {
                this.name = name;
                this.kills = kills;
                this.daysSinceDeath = daysSinceDeath;
            }

            /**
             * Get the name of the zombie.
             *
             * @return name of zombie
             */
            public String getName() {
                return name;
            }

            /**
             * Get the number of kills by the zombie.
             *
             * @return number of kill by the zombie
             */
            public int getKills() {
                return kills;
            }

            /**
             * Get the number of days since the zombie died.
             *
             * @return number of days since the zombie died
             */
            public int getDaysSinceDeath() {
                return daysSinceDeath;
            }

            /**
             * Set the name of the zombie.
             *
             * @param name name of the zombie
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * Set the number of kills by the zombie.
             *
             * @param kills number of kills by the zombie
             */
            public void setKills(int kills) {
                this.kills = kills;
            }

            /**
             * Set the days since the zombie died.
             *
             * @param daysSinceDeath days since the zombie died
             */
            public void setDaysSinceDeath(int daysSinceDeath) {
                this.daysSinceDeath = daysSinceDeath;
            }

            /**
             * Create a comparator that compares the names of the zombies.
             *
             * @return comparator that compares the names of the zombies
             */
            public Comparator<Zombie> getNameComparator() {
                return new Comparator<Zombie>() {
                    @Override
                    public int compare(Zombie zombie1, Zombie zombie2) {
                        return zombie1.getName().compareTo(zombie2.getName());
                    }
                };
            }

            /**
             * Create a comparator that compares the number of kills of each zombie.
             *
             * @return comparator that compares the number of kills
             */
            public Comparator<Zombie> getKillsComparator() {
                return new Comparator<Zombie>() {
                    @Override
                    public int compare(Zombie zombie1, Zombie zombie2) {
                        return zombie1.getKills() - zombie2.getKills();
                    }
                };
            }

            /**
             * Create a comparator that compares the number of days since death.
             *
             * @return comparator that compares the number of days since death
             */
            public Comparator<Zombie> getDaysSinceDeathComparator() {
                return new Comparator<Zombie>() {
                    @Override
                    public int compare(Zombie zombie1, Zombie zombie2) {
                        return zombie1.getKills() - zombie2.getKills();
                    }
                };
            }

        }
}