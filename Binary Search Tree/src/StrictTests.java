import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
//import struct.worth;
import org.junit.Before;
import org.junit.Test;


public class StrictTests {
    private BST<Integer> bst;
    private static final int TIMEOUT = 250;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst1() {
        bst.add(256);
        bst.add(-442);
        bst.add(-440);
        bst.add(130);
        bst.add(203);
        bst.add(-214);
        bst.add(417);
        bst.add(-393);
        bst.add(-135);

        assertTrue(bst.contains(256));
        assertTrue(bst.contains(-442));
        assertTrue(bst.contains(-440));
        assertTrue(bst.contains(130));
        assertTrue(bst.contains(203));
        assertTrue(bst.contains(-214));
        assertTrue(bst.contains(417));
        assertTrue(bst.contains(-393));
        assertTrue(bst.contains(-135));
        assertEquals(9, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst2() {
        bst.add(-158);
        bst.add(335);
        bst.add(142);
        bst.add(374);
        bst.add(-297);
        bst.add(-153);
        bst.add(-38);
        bst.add(-370);
        bst.add(-399);
        bst.add(-340);

        assertTrue(bst.contains(-158));
        assertTrue(bst.contains(335));
        assertTrue(bst.contains(142));
        assertTrue(bst.contains(374));
        assertTrue(bst.contains(-297));
        assertTrue(bst.contains(-153));
        assertTrue(bst.contains(-38));
        assertTrue(bst.contains(-370));
        assertTrue(bst.contains(-399));
        assertTrue(bst.contains(-340));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst3() {
        bst.add(377);
        bst.add(314);
        bst.add(-286);
        bst.add(Integer.MAX_VALUE);
        bst.add(-90);
        bst.add(-78);
        bst.add(31);
        bst.add(-165);
        bst.add(260);
        bst.add(280);
        bst.add(-54);
        bst.add(-214);

        assertTrue(bst.contains(377));
        assertTrue(bst.contains(314));
        assertTrue(bst.contains(-286));
        assertTrue(bst.contains(Integer.MAX_VALUE));
        assertTrue(bst.contains(-90));
        assertTrue(bst.contains(-78));
        assertTrue(bst.contains(31));
        assertTrue(bst.contains(-165));
        assertTrue(bst.contains(260));
        assertTrue(bst.contains(280));
        assertTrue(bst.contains(-54));
        assertTrue(bst.contains(-214));
        assertEquals(12, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst4() {
        bst.add(-176);
        bst.add(-194);
        bst.add(256);
        bst.add(-21);
        bst.add(-13);
        bst.add(-120);
        bst.add(-291);
        bst.add(-119);
        bst.add(-134);
        bst.add(-182);
        bst.add(-423);

        assertTrue(bst.contains(-176));
        assertTrue(bst.contains(-194));
        assertTrue(bst.contains(256));
        assertTrue(bst.contains(-21));
        assertTrue(bst.contains(-13));
        assertTrue(bst.contains(-120));
        assertTrue(bst.contains(-291));
        assertTrue(bst.contains(-119));
        assertTrue(bst.contains(-134));
        assertTrue(bst.contains(-182));
        assertTrue(bst.contains(-423));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst5() {
        bst.add(-287);
        bst.add(149);
        bst.add(-356);
        bst.add(224);
        bst.add(280);
        bst.add(28);
        bst.add(499);
        bst.add(428);
        bst.add(56);
        bst.add(-411);
        bst.add(109);
        bst.add(381);
        bst.add(84);
        bst.add(-421);

        assertTrue(bst.contains(-287));
        assertTrue(bst.contains(149));
        assertTrue(bst.contains(-356));
        assertTrue(bst.contains(224));
        assertTrue(bst.contains(280));
        assertTrue(bst.contains(28));
        assertTrue(bst.contains(499));
        assertTrue(bst.contains(428));
        assertTrue(bst.contains(56));
        assertTrue(bst.contains(-411));
        assertTrue(bst.contains(109));
        assertTrue(bst.contains(381));
        assertTrue(bst.contains(84));
        assertTrue(bst.contains(-421));
        assertEquals(14, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst6() {
        bst.add(287);
        bst.add(-351);
        bst.add(-225);
        bst.add(-464);
        bst.add(200);
        bst.add(-432);
        bst.add(423);
        bst.add(-299);
        bst.add(457);
        bst.add(190);
        bst.add(257);

        assertTrue(bst.contains(287));
        assertTrue(bst.contains(-351));
        assertTrue(bst.contains(-225));
        assertTrue(bst.contains(-464));
        assertTrue(bst.contains(200));
        assertTrue(bst.contains(-432));
        assertTrue(bst.contains(423));
        assertTrue(bst.contains(-299));
        assertTrue(bst.contains(457));
        assertTrue(bst.contains(190));
        assertTrue(bst.contains(257));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst7() {
        bst.add(51);
        bst.add(327);
        bst.add(-87);
        bst.add(127);
        bst.add(-373);
        bst.add(-153);
        bst.add(248);
        bst.add(-164);
        bst.add(-458);
        bst.add(-152);
        bst.add(-184);
        bst.add(-15);
        bst.add(-323);

        assertTrue(bst.contains(51));
        assertTrue(bst.contains(327));
        assertTrue(bst.contains(-87));
        assertTrue(bst.contains(127));
        assertTrue(bst.contains(-373));
        assertTrue(bst.contains(-153));
        assertTrue(bst.contains(248));
        assertTrue(bst.contains(-164));
        assertTrue(bst.contains(-458));
        assertTrue(bst.contains(-152));
        assertTrue(bst.contains(-184));
        assertTrue(bst.contains(-15));
        assertTrue(bst.contains(-323));
        assertEquals(13, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst8() {
        bst.add(Integer.MIN_VALUE);
        bst.add(-29);
        bst.add(421);
        bst.add(-317);
        bst.add(7);
        bst.add(-178);
        bst.add(-314);
        bst.add(483);
        bst.add(317);

        assertTrue(bst.contains(Integer.MIN_VALUE));
        assertTrue(bst.contains(-29));
        assertTrue(bst.contains(421));
        assertTrue(bst.contains(-317));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(-178));
        assertTrue(bst.contains(-314));
        assertTrue(bst.contains(483));
        assertTrue(bst.contains(317));
        assertEquals(9, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst9() {
        bst.add(12);
        bst.add(198);
        bst.add(-5);
        bst.add(172);
        bst.add(-151);
        bst.add(-110);
        bst.add(-486);
        bst.add(-294);
        bst.add(-495);
        bst.add(401);
        bst.add(230);

        assertTrue(bst.contains(12));
        assertTrue(bst.contains(198));
        assertTrue(bst.contains(-5));
        assertTrue(bst.contains(172));
        assertTrue(bst.contains(-151));
        assertTrue(bst.contains(-110));
        assertTrue(bst.contains(-486));
        assertTrue(bst.contains(-294));
        assertTrue(bst.contains(-495));
        assertTrue(bst.contains(401));
        assertTrue(bst.contains(230));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst10() {
        bst.add(-341);
        bst.add(-119);
        bst.add(-423);
        bst.add(446);
        bst.add(348);
        bst.add(10);
        bst.add(-156);
        bst.add(271);
        bst.add(249);
        bst.add(376);
        bst.add(373);
        bst.add(-343);

        assertTrue(bst.contains(-341));
        assertTrue(bst.contains(-119));
        assertTrue(bst.contains(-423));
        assertTrue(bst.contains(446));
        assertTrue(bst.contains(348));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(-156));
        assertTrue(bst.contains(271));
        assertTrue(bst.contains(249));
        assertTrue(bst.contains(376));
        assertTrue(bst.contains(373));
        assertTrue(bst.contains(-343));
        assertEquals(12, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst11() {
        bst.add(329);
        bst.add(16);
        bst.add(-148);
        bst.add(-343);
        bst.add(105);
        bst.add(-188);
        bst.add(54);
        bst.add(353);
        bst.add(86);
        bst.add(272);
        bst.add(36);
        bst.add(150);

        assertTrue(bst.contains(329));
        assertTrue(bst.contains(16));
        assertTrue(bst.contains(-148));
        assertTrue(bst.contains(-343));
        assertTrue(bst.contains(105));
        assertTrue(bst.contains(-188));
        assertTrue(bst.contains(54));
        assertTrue(bst.contains(353));
        assertTrue(bst.contains(86));
        assertTrue(bst.contains(272));
        assertTrue(bst.contains(36));
        assertTrue(bst.contains(150));
        assertEquals(12, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst12() {
        bst.add(250);
        bst.add(221);
        bst.add(-61);
        bst.add(187);
        bst.add(467);
        bst.add(-382);
        bst.add(43);
        bst.add(-264);
        bst.add(-272);
        bst.add(414);
        bst.add(-442);
        bst.add(457);
        bst.add(-215);
        bst.add(320);

        assertTrue(bst.contains(250));
        assertTrue(bst.contains(221));
        assertTrue(bst.contains(-61));
        assertTrue(bst.contains(187));
        assertTrue(bst.contains(467));
        assertTrue(bst.contains(-382));
        assertTrue(bst.contains(43));
        assertTrue(bst.contains(-264));
        assertTrue(bst.contains(-272));
        assertTrue(bst.contains(414));
        assertTrue(bst.contains(-442));
        assertTrue(bst.contains(457));
        assertTrue(bst.contains(-215));
        assertTrue(bst.contains(320));
        assertEquals(14, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst13() {
        bst.add(159);
        bst.add(47);
        bst.add(-239);
        bst.add(-245);
        bst.add(364);
        bst.add(-101);
        bst.add(-43);
        bst.add(249);
        bst.add(332);
        bst.add(-214);
        bst.add(485);

        assertTrue(bst.contains(159));
        assertTrue(bst.contains(47));
        assertTrue(bst.contains(-239));
        assertTrue(bst.contains(-245));
        assertTrue(bst.contains(364));
        assertTrue(bst.contains(-101));
        assertTrue(bst.contains(-43));
        assertTrue(bst.contains(249));
        assertTrue(bst.contains(332));
        assertTrue(bst.contains(-214));
        assertTrue(bst.contains(485));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst14() {
        bst.add(-421);
        bst.add(-256);
        bst.add(-5);
        bst.add(-203);
        bst.add(-23);
        bst.add(323);
        bst.add(481);
        bst.add(365);
        bst.add(-361);
        bst.add(250);
        bst.add(-431);
        bst.add(-129);
        bst.add(260);
        bst.add(-445);

        assertTrue(bst.contains(-421));
        assertTrue(bst.contains(-256));
        assertTrue(bst.contains(-5));
        assertTrue(bst.contains(-203));
        assertTrue(bst.contains(-23));
        assertTrue(bst.contains(323));
        assertTrue(bst.contains(481));
        assertTrue(bst.contains(365));
        assertTrue(bst.contains(-361));
        assertTrue(bst.contains(250));
        assertTrue(bst.contains(-431));
        assertTrue(bst.contains(-129));
        assertTrue(bst.contains(260));
        assertTrue(bst.contains(-445));
        assertEquals(14, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst15() {
        bst.add(414);
        bst.add(425);
        bst.add(-135);
        bst.add(196);
        bst.add(-408);
        bst.add(-133);
        bst.add(61);
        bst.add(-261);
        bst.add(-319);
        bst.add(318);
        bst.add(-375);
        bst.add(-27);
        bst.add(-457);

        assertTrue(bst.contains(414));
        assertTrue(bst.contains(425));
        assertTrue(bst.contains(-135));
        assertTrue(bst.contains(196));
        assertTrue(bst.contains(-408));
        assertTrue(bst.contains(-133));
        assertTrue(bst.contains(61));
        assertTrue(bst.contains(-261));
        assertTrue(bst.contains(-319));
        assertTrue(bst.contains(318));
        assertTrue(bst.contains(-375));
        assertTrue(bst.contains(-27));
        assertTrue(bst.contains(-457));
        assertEquals(13, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst16() {
        bst.add(-88);
        bst.add(-226);
        bst.add(346);
        bst.add(-356);
        bst.add(-360);
        bst.add(-371);
        bst.add(139);
        bst.add(-396);
        bst.add(402);
        bst.add(425);
        bst.add(-287);
        bst.add(151);
        bst.add(-294);

        assertTrue(bst.contains(-88));
        assertTrue(bst.contains(-226));
        assertTrue(bst.contains(346));
        assertTrue(bst.contains(-356));
        assertTrue(bst.contains(-360));
        assertTrue(bst.contains(-371));
        assertTrue(bst.contains(139));
        assertTrue(bst.contains(-396));
        assertTrue(bst.contains(402));
        assertTrue(bst.contains(425));
        assertTrue(bst.contains(-287));
        assertTrue(bst.contains(151));
        assertTrue(bst.contains(-294));
        assertEquals(13, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst17() {
        bst.add(-316);
        bst.add(234);
        bst.add(-275);
        bst.add(12);
        bst.add(-376);
        bst.add(-240);
        bst.add(3);
        bst.add(149);
        bst.add(-254);
        bst.add(-391);

        assertTrue(bst.contains(-316));
        assertTrue(bst.contains(234));
        assertTrue(bst.contains(-275));
        assertTrue(bst.contains(12));
        assertTrue(bst.contains(-376));
        assertTrue(bst.contains(-240));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(149));
        assertTrue(bst.contains(-254));
        assertTrue(bst.contains(-391));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst18() {
        bst.add(-55);
        bst.add(-86);
        bst.add(49);
        bst.add(-173);
        bst.add(134);
        bst.add(363);
        bst.add(-441);
        bst.add(380);
        bst.add(474);
        bst.add(-326);
        bst.add(-230);
        bst.add(-353);
        bst.add(-365);
        bst.add(-156);
        bst.add(-354);

        assertTrue(bst.contains(-55));
        assertTrue(bst.contains(-86));
        assertTrue(bst.contains(49));
        assertTrue(bst.contains(-173));
        assertTrue(bst.contains(134));
        assertTrue(bst.contains(363));
        assertTrue(bst.contains(-441));
        assertTrue(bst.contains(380));
        assertTrue(bst.contains(474));
        assertTrue(bst.contains(-326));
        assertTrue(bst.contains(-230));
        assertTrue(bst.contains(-353));
        assertTrue(bst.contains(-365));
        assertTrue(bst.contains(-156));
        assertTrue(bst.contains(-354));
        assertEquals(15, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst19() {
        bst.add(-408);
        bst.add(47);
        bst.add(419);
        bst.add(-340);
        bst.add(-30);
        bst.add(177);
        bst.add(-328);
        bst.add(-315);
        bst.add(-432);
        bst.add(-321);

        assertTrue(bst.contains(-408));
        assertTrue(bst.contains(47));
        assertTrue(bst.contains(419));
        assertTrue(bst.contains(-340));
        assertTrue(bst.contains(-30));
        assertTrue(bst.contains(177));
        assertTrue(bst.contains(-328));
        assertTrue(bst.contains(-315));
        assertTrue(bst.contains(-432));
        assertTrue(bst.contains(-321));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void addTestbst20() {
        bst.add(-403);
        bst.add(-191);
        bst.add(-90);
        bst.add(-71);
        bst.add(133);
        bst.add(-31);
        bst.add(-182);
        bst.add(296);
        bst.add(312);
        bst.add(-81);
        bst.add(62);
        bst.add(121);
        bst.add(-500);

        assertTrue(bst.contains(-403));
        assertTrue(bst.contains(-191));
        assertTrue(bst.contains(-90));
        assertTrue(bst.contains(-71));
        assertTrue(bst.contains(133));
        assertTrue(bst.contains(-31));
        assertTrue(bst.contains(-182));
        assertTrue(bst.contains(296));
        assertTrue(bst.contains(312));
        assertTrue(bst.contains(-81));
        assertTrue(bst.contains(62));
        assertTrue(bst.contains(121));
        assertTrue(bst.contains(-500));
        assertEquals(13, bst.size());
    }



    @Test(timeout = TIMEOUT)
    public void removeTestbst1() {
        bst.add(-22);
        bst.add(-7);
        bst.add(-489);
        bst.add(348);
        bst.add(222);
        bst.add(-242);
        bst.add(236);
        bst.add(446);
        bst.add(21);
        bst.add(289);
        bst.add(-169);
        bst.add(-96);
        bst.add(-18);
        bst.remove(21);
        bst.remove(222);

        assertTrue(bst.contains(-22));
        assertTrue(bst.contains(-7));
        assertTrue(bst.contains(-489));
        assertTrue(bst.contains(348));
        assertTrue(bst.contains(-242));
        assertTrue(bst.contains(236));
        assertTrue(bst.contains(446));
        assertTrue(bst.contains(289));
        assertTrue(bst.contains(-169));
        assertTrue(bst.contains(-96));
        assertTrue(bst.contains(-18));
        assertFalse(bst.contains(21));
        assertFalse(bst.contains(222));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst2() {
        bst.add(101);
        bst.add(112);
        bst.add(-116);
        bst.add(-416);
        bst.add(17);
        bst.add(-209);
        bst.add(-496);
        bst.add(-104);
        bst.add(492);
        bst.add(245);
        bst.add(-224);
        bst.add(0);
        bst.add(93);
        bst.add(277);
        bst.add(165);
        bst.remove(-104);
        bst.remove(17);
        bst.remove(-209);

        assertTrue(bst.contains(101));
        assertTrue(bst.contains(112));
        assertTrue(bst.contains(-116));
        assertTrue(bst.contains(-416));
        assertTrue(bst.contains(-496));
        assertTrue(bst.contains(492));
        assertTrue(bst.contains(245));
        assertTrue(bst.contains(-224));
        assertTrue(bst.contains(0));
        assertTrue(bst.contains(93));
        assertTrue(bst.contains(277));
        assertTrue(bst.contains(165));
        assertFalse(bst.contains(-104));
        assertFalse(bst.contains(17));
        assertFalse(bst.contains(-209));
        assertEquals(12, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst3() {
        bst.add(-414);
        bst.add(252);
        bst.add(470);
        bst.add(453);
        bst.add(-33);
        bst.add(-117);
        bst.add(45);
        bst.add(216);
        bst.add(238);
        bst.add(333);
        bst.add(-168);
        bst.add(-135);
        bst.remove(45);
        bst.remove(-168);

        assertTrue(bst.contains(-414));
        assertTrue(bst.contains(252));
        assertTrue(bst.contains(470));
        assertTrue(bst.contains(453));
        assertTrue(bst.contains(-33));
        assertTrue(bst.contains(-117));
        assertTrue(bst.contains(216));
        assertTrue(bst.contains(238));
        assertTrue(bst.contains(333));
        assertTrue(bst.contains(-135));
        assertFalse(bst.contains(45));
        assertFalse(bst.contains(-168));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst4() {
        bst.add(-69);
        bst.add(133);
        bst.add(-418);
        bst.add(263);
        bst.add(-93);
        bst.add(-37);
        bst.add(-355);
        bst.add(-169);
        bst.add(74);
        bst.add(487);
        bst.add(-357);
        bst.add(-224);
        bst.add(-59);
        bst.add(-359);
        bst.add(-450);
        bst.remove(133);
        bst.remove(74);
        bst.remove(-224);
        bst.remove(487);
        bst.remove(-359);

        assertTrue(bst.contains(-69));
        assertTrue(bst.contains(-418));
        assertTrue(bst.contains(263));
        assertTrue(bst.contains(-93));
        assertTrue(bst.contains(-37));
        assertTrue(bst.contains(-355));
        assertTrue(bst.contains(-169));
        assertTrue(bst.contains(-357));
        assertTrue(bst.contains(-59));
        assertTrue(bst.contains(-450));
        assertFalse(bst.contains(133));
        assertFalse(bst.contains(74));
        assertFalse(bst.contains(-224));
        assertFalse(bst.contains(487));
        assertFalse(bst.contains(-359));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst5() {
        bst.add(-386);
        bst.add(-26);
        bst.add(-58);
        bst.add(154);
        bst.add(-445);
        bst.add(11);
        bst.add(-460);
        bst.add(-259);
        bst.add(-99);
        bst.add(-23);
        bst.add(337);
        bst.remove(11);
        bst.remove(-26);
        bst.remove(337);
        bst.remove(-99);
        bst.remove(-23);

        assertTrue(bst.contains(-386));
        assertTrue(bst.contains(-58));
        assertTrue(bst.contains(154));
        assertTrue(bst.contains(-445));
        assertTrue(bst.contains(-460));
        assertTrue(bst.contains(-259));
        assertFalse(bst.contains(11));
        assertFalse(bst.contains(-26));
        assertFalse(bst.contains(337));
        assertFalse(bst.contains(-99));
        assertFalse(bst.contains(-23));
        assertEquals(6, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst6() {
        bst.add(130);
        bst.add(-71);
        bst.add(302);
        bst.add(-180);
        bst.add(-472);
        bst.add(-407);
        bst.add(-382);
        bst.add(-204);
        bst.add(250);
        bst.add(-395);
        bst.remove(-180);
        bst.remove(-204);
        bst.remove(-71);
        bst.remove(-395);

        assertTrue(bst.contains(130));
        assertTrue(bst.contains(302));
        assertTrue(bst.contains(-472));
        assertTrue(bst.contains(-407));
        assertTrue(bst.contains(-382));
        assertTrue(bst.contains(250));
        assertFalse(bst.contains(-180));
        assertFalse(bst.contains(-204));
        assertFalse(bst.contains(-71));
        assertFalse(bst.contains(-395));
        assertEquals(6, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst7() {
        bst.add(-68);
        bst.add(-229);
        bst.add(381);
        bst.add(-239);
        bst.add(-245);
        bst.add(-418);
        bst.add(382);
        bst.add(217);
        bst.add(336);
        bst.add(374);
        bst.add(-500);
        bst.add(-346);
        bst.add(-266);
        bst.add(414);
        bst.remove(-346);
        bst.remove(336);
        bst.remove(-239);
        bst.remove(-500);

        assertTrue(bst.contains(-68));
        assertTrue(bst.contains(-229));
        assertTrue(bst.contains(381));
        assertTrue(bst.contains(-245));
        assertTrue(bst.contains(-418));
        assertTrue(bst.contains(382));
        assertTrue(bst.contains(217));
        assertTrue(bst.contains(374));
        assertTrue(bst.contains(-266));
        assertTrue(bst.contains(414));
        assertFalse(bst.contains(-346));
        assertFalse(bst.contains(336));
        assertFalse(bst.contains(-239));
        assertFalse(bst.contains(-500));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst8() {
        bst.add(343);
        bst.add(-487);
        bst.add(-110);
        bst.add(264);
        bst.add(-347);
        bst.add(-219);
        bst.add(36);
        bst.add(-325);
        bst.add(-376);
        bst.add(-46);
        bst.remove(343);
        bst.remove(-347);

        assertTrue(bst.contains(-487));
        assertTrue(bst.contains(-110));
        assertTrue(bst.contains(264));
        assertTrue(bst.contains(-219));
        assertTrue(bst.contains(36));
        assertTrue(bst.contains(-325));
        assertTrue(bst.contains(-376));
        assertTrue(bst.contains(-46));
        assertFalse(bst.contains(343));
        assertFalse(bst.contains(-347));
        assertEquals(8, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst9() {
        bst.add(465);
        bst.add(186);
        bst.add(-144);
        bst.add(-326);
        bst.add(-394);
        bst.add(231);
        bst.add(-87);
        bst.add(-197);
        bst.add(215);
        bst.add(147);
        bst.add(-27);
        bst.add(199);
        bst.add(145);
        bst.add(-430);
        bst.add(-292);
        bst.remove(186);
        bst.remove(-27);
        bst.remove(465);
        bst.remove(145);

        assertTrue(bst.contains(-144));
        assertTrue(bst.contains(-326));
        assertTrue(bst.contains(-394));
        assertTrue(bst.contains(231));
        assertTrue(bst.contains(-87));
        assertTrue(bst.contains(-197));
        assertTrue(bst.contains(215));
        assertTrue(bst.contains(147));
        assertTrue(bst.contains(199));
        assertTrue(bst.contains(-430));
        assertTrue(bst.contains(-292));
        assertFalse(bst.contains(186));
        assertFalse(bst.contains(-27));
        assertFalse(bst.contains(465));
        assertFalse(bst.contains(145));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst10() {
        bst.add(181);
        bst.add(-324);
        bst.add(-106);
        bst.add(71);
        bst.add(386);
        bst.add(301);
        bst.add(-225);
        bst.add(43);
        bst.add(475);
        bst.add(59);
        bst.add(345);
        bst.add(-293);
        bst.remove(301);
        bst.remove(345);

        assertTrue(bst.contains(181));
        assertTrue(bst.contains(-324));
        assertTrue(bst.contains(-106));
        assertTrue(bst.contains(71));
        assertTrue(bst.contains(386));
        assertTrue(bst.contains(-225));
        assertTrue(bst.contains(43));
        assertTrue(bst.contains(475));
        assertTrue(bst.contains(59));
        assertTrue(bst.contains(-293));
        assertFalse(bst.contains(301));
        assertFalse(bst.contains(345));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst11() {
        bst.add(330);
        bst.add(231);
        bst.add(-273);
        bst.add(-297);
        bst.add(280);
        bst.add(76);
        bst.add(-272);
        bst.add(-201);
        bst.add(-233);
        bst.add(189);
        bst.add(-227);
        bst.add(323);
        bst.add(-84);
        bst.add(-388);
        bst.remove(-233);
        bst.remove(-297);
        bst.remove(-201);
        bst.remove(330);

        assertTrue(bst.contains(231));
        assertTrue(bst.contains(-273));
        assertTrue(bst.contains(280));
        assertTrue(bst.contains(76));
        assertTrue(bst.contains(-272));
        assertTrue(bst.contains(189));
        assertTrue(bst.contains(-227));
        assertTrue(bst.contains(323));
        assertTrue(bst.contains(-84));
        assertTrue(bst.contains(-388));
        assertFalse(bst.contains(-233));
        assertFalse(bst.contains(-297));
        assertFalse(bst.contains(-201));
        assertFalse(bst.contains(330));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst12() {
        bst.add(485);
        bst.add(-429);
        bst.add(-112);
        bst.add(375);
        bst.add(301);
        bst.add(79);
        bst.add(-473);
        bst.add(252);
        bst.add(-31);
        bst.add(270);
        bst.add(-228);
        bst.add(372);
        bst.add(215);
        bst.add(34);
        bst.remove(-473);
        bst.remove(-228);
        bst.remove(79);

        assertTrue(bst.contains(485));
        assertTrue(bst.contains(-429));
        assertTrue(bst.contains(-112));
        assertTrue(bst.contains(375));
        assertTrue(bst.contains(301));
        assertTrue(bst.contains(252));
        assertTrue(bst.contains(-31));
        assertTrue(bst.contains(270));
        assertTrue(bst.contains(372));
        assertTrue(bst.contains(215));
        assertTrue(bst.contains(34));
        assertFalse(bst.contains(-473));
        assertFalse(bst.contains(-228));
        assertFalse(bst.contains(79));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst13() {
        bst.add(110);
        bst.add(107);
        bst.add(-145);
        bst.add(2);
        bst.add(92);
        bst.add(-438);
        bst.add(483);
        bst.add(96);
        bst.add(-335);
        bst.add(158);
        bst.add(72);
        bst.add(-146);
        bst.add(164);
        bst.remove(2);
        bst.remove(-438);
        bst.remove(110);
        bst.remove(72);
        bst.remove(-146);

        assertTrue(bst.contains(107));
        assertTrue(bst.contains(-145));
        assertTrue(bst.contains(92));
        assertTrue(bst.contains(483));
        assertTrue(bst.contains(96));
        assertTrue(bst.contains(-335));
        assertTrue(bst.contains(158));
        assertTrue(bst.contains(164));
        assertFalse(bst.contains(2));
        assertFalse(bst.contains(-438));
        assertFalse(bst.contains(110));
        assertFalse(bst.contains(72));
        assertFalse(bst.contains(-146));
        assertEquals(8, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst14() {
        bst.add(-246);
        bst.add(66);
        bst.add(-289);
        bst.add(-232);
        bst.add(-391);
        bst.add(374);
        bst.add(-312);
        bst.add(305);
        bst.add(191);
        bst.add(-260);
        bst.add(-24);
        bst.add(-412);
        bst.add(-402);
        bst.add(291);
        bst.add(41);
        bst.remove(-312);
        bst.remove(-246);
        bst.remove(-289);
        bst.remove(191);

        assertTrue(bst.contains(66));
        assertTrue(bst.contains(-232));
        assertTrue(bst.contains(-391));
        assertTrue(bst.contains(374));
        assertTrue(bst.contains(305));
        assertTrue(bst.contains(-260));
        assertTrue(bst.contains(-24));
        assertTrue(bst.contains(-412));
        assertTrue(bst.contains(-402));
        assertTrue(bst.contains(291));
        assertTrue(bst.contains(41));
        assertFalse(bst.contains(-312));
        assertFalse(bst.contains(-246));
        assertFalse(bst.contains(-289));
        assertFalse(bst.contains(191));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst15() {
        bst.add(-381);
        bst.add(-237);
        bst.add(300);
        bst.add(-246);
        bst.add(-181);
        bst.add(406);
        bst.add(383);
        bst.add(292);
        bst.add(291);
        bst.add(-440);
        bst.add(-443);
        bst.add(426);
        bst.add(-15);
        bst.remove(-246);
        bst.remove(426);
        bst.remove(-181);

        assertTrue(bst.contains(-381));
        assertTrue(bst.contains(-237));
        assertTrue(bst.contains(300));
        assertTrue(bst.contains(406));
        assertTrue(bst.contains(383));
        assertTrue(bst.contains(292));
        assertTrue(bst.contains(291));
        assertTrue(bst.contains(-440));
        assertTrue(bst.contains(-443));
        assertTrue(bst.contains(-15));
        assertFalse(bst.contains(-246));
        assertFalse(bst.contains(426));
        assertFalse(bst.contains(-181));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst16() {
        bst.add(3);
        bst.add(461);
        bst.add(-355);
        bst.add(-218);
        bst.add(34);
        bst.add(214);
        bst.add(-49);
        bst.add(-322);
        bst.add(470);
        bst.add(147);
        bst.add(-140);
        bst.add(155);
        bst.remove(147);
        bst.remove(-218);
        bst.remove(214);
        bst.remove(-49);

        assertTrue(bst.contains(3));
        assertTrue(bst.contains(461));
        assertTrue(bst.contains(-355));
        assertTrue(bst.contains(34));
        assertTrue(bst.contains(-322));
        assertTrue(bst.contains(470));
        assertTrue(bst.contains(-140));
        assertTrue(bst.contains(155));
        assertFalse(bst.contains(147));
        assertFalse(bst.contains(-218));
        assertFalse(bst.contains(214));
        assertFalse(bst.contains(-49));
        assertEquals(8, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst17() {
        bst.add(348);
        bst.add(-262);
        bst.add(20);
        bst.add(111);
        bst.add(430);
        bst.add(232);
        bst.add(-16);
        bst.add(226);
        bst.add(76);
        bst.add(-68);
        bst.add(-176);
        bst.remove(111);
        bst.remove(-68);

        assertTrue(bst.contains(348));
        assertTrue(bst.contains(-262));
        assertTrue(bst.contains(20));
        assertTrue(bst.contains(430));
        assertTrue(bst.contains(232));
        assertTrue(bst.contains(-16));
        assertTrue(bst.contains(226));
        assertTrue(bst.contains(76));
        assertTrue(bst.contains(-176));
        assertFalse(bst.contains(111));
        assertFalse(bst.contains(-68));
        assertEquals(9, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst18() {
        bst.add(181);
        bst.add(119);
        bst.add(-23);
        bst.add(-373);
        bst.add(-101);
        bst.add(191);
        bst.add(74);
        bst.add(-3);
        bst.add(249);
        bst.add(57);
        bst.add(455);
        bst.add(-116);
        bst.remove(249);
        bst.remove(-101);
        bst.remove(-116);

        assertTrue(bst.contains(181));
        assertTrue(bst.contains(119));
        assertTrue(bst.contains(-23));
        assertTrue(bst.contains(-373));
        assertTrue(bst.contains(191));
        assertTrue(bst.contains(74));
        assertTrue(bst.contains(-3));
        assertTrue(bst.contains(57));
        assertTrue(bst.contains(455));
        assertFalse(bst.contains(249));
        assertFalse(bst.contains(-101));
        assertFalse(bst.contains(-116));
        assertEquals(9, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst19() {
        bst.add(-197);
        bst.add(-19);
        bst.add(419);
        bst.add(485);
        bst.add(173);
        bst.add(-279);
        bst.add(-402);
        bst.add(20);
        bst.add(97);
        bst.add(397);
        bst.add(311);
        bst.add(437);
        bst.add(285);
        bst.add(177);
        bst.remove(20);
        bst.remove(485);
        bst.remove(-197);
        bst.remove(177);

        assertTrue(bst.contains(-19));
        assertTrue(bst.contains(419));
        assertTrue(bst.contains(173));
        assertTrue(bst.contains(-279));
        assertTrue(bst.contains(-402));
        assertTrue(bst.contains(97));
        assertTrue(bst.contains(397));
        assertTrue(bst.contains(311));
        assertTrue(bst.contains(437));
        assertTrue(bst.contains(285));
        assertFalse(bst.contains(20));
        assertFalse(bst.contains(485));
        assertFalse(bst.contains(-197));
        assertFalse(bst.contains(177));
        assertEquals(10, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeTestbst20() {
        bst.add(-116);
        bst.add(-376);
        bst.add(-170);
        bst.add(-279);
        bst.add(-346);
        bst.add(-332);
        bst.add(204);
        bst.add(160);
        bst.add(107);
        bst.add(-250);
        bst.add(56);
        bst.add(69);
        bst.add(477);
        bst.add(189);
        bst.remove(69);
        bst.remove(107);
        bst.remove(-376);

        assertTrue(bst.contains(-116));
        assertTrue(bst.contains(-170));
        assertTrue(bst.contains(-279));
        assertTrue(bst.contains(-346));
        assertTrue(bst.contains(-332));
        assertTrue(bst.contains(204));
        assertTrue(bst.contains(160));
        assertTrue(bst.contains(-250));
        assertTrue(bst.contains(56));
        assertTrue(bst.contains(477));
        assertTrue(bst.contains(189));
        assertFalse(bst.contains(69));
        assertFalse(bst.contains(107));
        assertFalse(bst.contains(-376));
        assertEquals(11, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void getTestbst1() {
        bst.add(405);
        bst.add(159);
        bst.add(-10);
        bst.add(214);
        bst.add(475);
        bst.add(81);
        bst.add(-43);
        bst.add(-493);
        bst.add(192);
        bst.add(0);
        bst.add(-161);

        assertEquals((Integer) (405), bst.get(405));
        assertEquals((Integer) (159), bst.get(159));
        assertEquals((Integer) (-10), bst.get(-10));
        assertEquals((Integer) (214), bst.get(214));
        assertEquals((Integer) (475), bst.get(475));
        assertEquals((Integer) (81), bst.get(81));
        assertEquals((Integer) (-43), bst.get(-43));
        assertEquals((Integer) (-493), bst.get(-493));
        assertEquals((Integer) (192), bst.get(192));
        assertEquals((Integer) (0), bst.get(0));
        assertEquals((Integer) (-161), bst.get(-161));
    }

    @Test(timeout = TIMEOUT)
    public void getTestbst2() {
        bst.add(-125);
        bst.add(43);
        bst.add(-350);
        bst.add(-170);
        bst.add(259);
        bst.add(473);
        bst.add(-181);
        bst.add(292);
        bst.add(-189);
        bst.add(-225);
        bst.add(187);
        bst.add(-148);

        assertEquals((Integer) (-125), bst.get(-125));
        assertEquals((Integer) (43), bst.get(43));
        assertEquals((Integer) (-350), bst.get(-350));
        assertEquals((Integer) (-170), bst.get(-170));
        assertEquals((Integer) (259), bst.get(259));
        assertEquals((Integer) (473), bst.get(473));
        assertEquals((Integer) (-181), bst.get(-181));
        assertEquals((Integer) (292), bst.get(292));
        assertEquals((Integer) (-189), bst.get(-189));
        assertEquals((Integer) (-225), bst.get(-225));
        assertEquals((Integer) (187), bst.get(187));
        assertEquals((Integer) (-148), bst.get(-148));
    }

    @Test(timeout = TIMEOUT)
    public void getTestbst3() {
        bst.add(140);
        bst.add(-202);
        bst.add(-351);
        bst.add(331);
        bst.add(29);
        bst.add(-488);
        bst.add(-34);
        bst.add(63);
        bst.add(-27);
        bst.add(-320);
        bst.add(-190);
        bst.add(308);
        bst.add(-176);

        assertEquals((Integer) (140), bst.get(140));
        assertEquals((Integer) (-202), bst.get(-202));
        assertEquals((Integer) (-351), bst.get(-351));
        assertEquals((Integer) (331), bst.get(331));
        assertEquals((Integer) (29), bst.get(29));
        assertEquals((Integer) (-488), bst.get(-488));
        assertEquals((Integer) (-34), bst.get(-34));
        assertEquals((Integer) (63), bst.get(63));
        assertEquals((Integer) (-27), bst.get(-27));
        assertEquals((Integer) (-320), bst.get(-320));
        assertEquals((Integer) (-190), bst.get(-190));
        assertEquals((Integer) (308), bst.get(308));
        assertEquals((Integer) (-176), bst.get(-176));
    }

    @Test(timeout = TIMEOUT)
    public void getTestbst4() {
        bst.add(-292);
        bst.add(386);
        bst.add(-321);
        bst.add(-210);
        bst.add(65);
        bst.add(-47);
        bst.add(380);
        bst.add(-339);
        bst.add(-400);
        bst.add(-337);
        bst.add(206);
        bst.add(-71);

        assertEquals((Integer) (-292), bst.get(-292));
        assertEquals((Integer) (386), bst.get(386));
        assertEquals((Integer) (-321), bst.get(-321));
        assertEquals((Integer) (-210), bst.get(-210));
        assertEquals((Integer) (65), bst.get(65));
        assertEquals((Integer) (-47), bst.get(-47));
        assertEquals((Integer) (-210), bst.get(-210));
        assertEquals((Integer) (380), bst.get(380));
        assertEquals((Integer) (-339), bst.get(-339));
        assertEquals((Integer) (-400), bst.get(-400));
        assertEquals((Integer) (-337), bst.get(-337));
        assertEquals((Integer) (206), bst.get(206));
        assertEquals((Integer) (-71), bst.get(-71));
    }

    @Test(timeout = TIMEOUT)
    public void getTestbst5() {
        bst.add(-291);
        bst.add(-178);
        bst.add(-137);
        bst.add(-344);
        bst.add(219);
        bst.add(-118);
        bst.add(-11);
        bst.add(200);
        bst.add(66);
        bst.add(-354);
        bst.add(8);
        bst.add(-72);
        bst.add(470);
        bst.add(-76);

        assertEquals((Integer) (-291), bst.get(-291));
        assertEquals((Integer) (-178), bst.get(-178));
        assertEquals((Integer) (-137), bst.get(-137));
        assertEquals((Integer) (-344), bst.get(-344));
        assertEquals((Integer) (219), bst.get(219));
        assertEquals((Integer) (-118), bst.get(-118));
        assertEquals((Integer) (-11), bst.get(-11));
        assertEquals((Integer) (200), bst.get(200));
        assertEquals((Integer) (66), bst.get(66));
        assertEquals((Integer) (-354), bst.get(-354));
        assertEquals((Integer) (8), bst.get(8));
        assertEquals((Integer) (-72), bst.get(-72));
        assertEquals((Integer) (470), bst.get(470));
        assertEquals((Integer) (-76), bst.get(-76));
    }


    @Test(timeout = TIMEOUT)
    public void preOrderTest1() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(10);
        t0.add(-5);
        t0.add(20);
        t0.add(30);
        t0.add(25);

        List<Integer> l0 = t0.preorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(10);
        l1.add(-5);
        l1.add(20);
        l1.add(30);
        l1.add(25);

        assertEquals(l0, l1);
    }

    @Test(timeout = TIMEOUT)
    public void preOrderTest2() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(Integer.MAX_VALUE);
        t0.add(Integer.MIN_VALUE);
        t0.add(10);
        t0.add(20);

        List<Integer> l0 = t0.preorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(Integer.MAX_VALUE);
        l1.add(Integer.MIN_VALUE);
        l1.add(10);
        l1.add(20);

        assertEquals(l0, l1);
    }

    @Test(timeout = TIMEOUT)
    public void inOrderTest1() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(1);
        t0.add(2);
        t0.add(-99);
        t0.add(100);
        t0.add(20);

        List<Integer> l0 = t0.inorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(-99);
        l1.add(1);
        l1.add(2);
        l1.add(20);
        l1.add(100);

        assertEquals(l0, l1);
    }

    @Test(timeout = TIMEOUT)
    public void inOrderTest2() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(Integer.MAX_VALUE);
        t0.add(Integer.MIN_VALUE);
        t0.add(10);
        t0.add(-10);
        t0.add(20);

        List<Integer> l0 = t0.inorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(Integer.MIN_VALUE);
        l1.add(-10);
        l1.add(10);
        l1.add(20);
        l1.add(Integer.MAX_VALUE);

        assertEquals(l0, l1);
    }

    @Test(timeout = TIMEOUT)
    public void postOrderTest1() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(10);
        t0.add(-5);
        t0.add(15);
        t0.add(20);
        t0.add(-15);

        List<Integer> l0 = t0.postorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(-15);
        l1.add(-5);
        l1.add(20);
        l1.add(15);
        l1.add(10);
        assertEquals(l0, l1);
    }

    @Test(timeout = TIMEOUT)
    public void postOrderTest2() {
        BST<Integer> t0 = new BST<Integer>();
        t0.add(Integer.MAX_VALUE);
        t0.add(Integer.MIN_VALUE);
        t0.add(15);
        t0.add(-15);
        t0.add(20);

        List<Integer> l0 = t0.postorder();

        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(-15);
        l1.add(20);
        l1.add(15);
        l1.add(Integer.MIN_VALUE);
        l1.add(Integer.MAX_VALUE);

        assertEquals(l0, l1);
    }


    @After
    public void teardown() {
        bst = null;
    }
}
