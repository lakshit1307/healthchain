package com.healthedge.healthchain.common.util;
import java.security.SecureRandom;


public class PasswordGenerator {



    public static final int MIN_LENGTH = 6;

    /**
     * The random number generator.
     */
    protected static final SecureRandom r = new SecureRandom();

    public final static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
    public final static char[] number = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public final static char[] specialChar = {'@', '#', '$', '%', '^', '+', '=', '_'};

    public static String get(int size, boolean isComplex) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; sb.length() <= size; i++) {
            sb.append(goodChar[r.nextInt(goodChar.length)]);
            if (size == sb.length())
                break;
            sb.append(number[r.nextInt(number.length)]);
            if (size == sb.length())
                break;

            if (isComplex) {
                sb.append(specialChar[r.nextInt(specialChar.length)]);
                if (size == sb.length())
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        System.out.println(generator.get(5, false));
    }

}