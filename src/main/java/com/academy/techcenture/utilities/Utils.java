package com.academy.techcenture.utilities;

public class Utils {

    /**
     * This method generates random number between min and max.
     * @param min Min is included
     * @param max Min is included
     * @return int
     */
    public static String generateRandomNumber(int min, int max){
        String result = Integer.toString((int)(Math.random() * ((max-min)+1)) + min);
        return (result.length() < 2 ? 0 + result : result);
    }

    /**
     * This method generate random number longs "Numbers" symbols.
     * @param numbers is characterize how long number will be.
     * @return String
     */
    public static String generateRandomNumber(int numbers){
        String result = "";
        for (int i = 0; i < numbers; i++) {
            result += Integer.toString((int)(Math.random() * 10));
        }
        return result;
    }

}