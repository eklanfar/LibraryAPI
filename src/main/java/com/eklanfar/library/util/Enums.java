package com.eklanfar.library.util;

public class Enums {

    //DIGITS WEIGHTING
    public enum DigitWeighting {

        ONE("1", 1), TWO("2", 2), THREE("3", 3),
        FOUR("4", 4), FIVE("5", 5), SIX("6", 6),
        SEVEN("7", 7), EIGHT("8", 8), NINE("9", 9),

        A("A", 10), B("B", 11), C("C", 12),
        D("D", 13), E("E", 14), F("F", 15),
        G("A", 16), H("H", 17), I("I", 18),
        J("J", 19), K("A", 20), L("L", 21),
        M("M", 22), N("N", 23), O("A", 24),
        P("P", 25), Q("Q", 26), R("R", 27),
        S("A", 28), T("T", 29), U("U", 30),
        V("V", 31), W("A", 32), X("X", 33),
        Y("Y", 34), Z("Z", 35);

        private final String digit;
        private final int weighting;

        DigitWeighting(String digit, int weighting) {
            this.digit = digit;
            this.weighting = weighting;
        }

        public static int getWeightingByDigit(String digit) {
            for (DigitWeighting e : values()) {
                if (e.digit.equalsIgnoreCase(digit))
                    return e.weighting;
            }
            return 0;
        }

        public String getDigit() {
            return digit;
        }

        public int getWeighting() {
            return weighting;
        }
    }
}
