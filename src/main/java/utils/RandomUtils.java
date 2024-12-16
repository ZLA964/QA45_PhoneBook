package utils;

import java.util.Random;

public class RandomUtils {

    static Random random = new Random();

    public static String generateString(int length){
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] randomString = new char[length];
        int index;
        int charLength = characters.length();
        for(int i =0; i < length; i++){
            index = random.nextInt(charLength);
            randomString[i]=characters.charAt(index);
        }
        return new String(randomString);
    }

    public static String generateEmail(int length){
        String[] domains ={"gmail.com","mail.ru", "yahoo.com", "yandex.ru"};
        String domain = domains[random.nextInt(domains.length)];
        return (generateString(length)+"@"+ domain);
    }

    public static String generatePhone(int length){
        String characters = "0123456789";
        char[] randomString = new char[length];
        int index;
        int charLength = characters.length();
        for(int i =0; i < length; i++){
            index = random.nextInt(charLength);
            randomString[i]=characters.charAt(index);
        }
        return new String(randomString);
    }
}
