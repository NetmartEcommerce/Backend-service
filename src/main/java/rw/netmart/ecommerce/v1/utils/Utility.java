package rw.netmart.ecommerce.v1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;

public class Utility {
    private static final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUM = "0123456789";

    private static final Random rng = new SecureRandom();

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    static char randomChar(){return ALPHANUM.charAt(rng.nextInt(ALPHANUM.length()));}

    static char randomNum(){return NUM.charAt(rng.nextInt(NUM.length()));}


    public static char randomStr() {
        return ALPHA.charAt(rng.nextInt(ALPHA.length()));
    }


    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    public static String randomUUID(int length, int spacing, char returnType) {
        StringBuilder sb = new StringBuilder();
        char spacerChar = '-';
        int spacer = 0;
        while (length > 0) {
            if (spacer == spacing && spacing > 0) {
                spacer++;
                sb.append(spacerChar);
            }
            length--;
            spacer++;

            switch (returnType) {
                case 'A':
                    sb.append(randomChar());
                    break;
                case 'N':
                    sb.append(randomNum());
                    break;
                case 'S':
                    sb.append(randomStr());
                    break;
                default:
                    logger.error("");
                    break;
            }
        }
        return sb.toString();
    }


}
