package utils;

import lombok.Getter;

@Getter
public class TestsDates {
    private final String[] logins;
    private final String[] passwords;

    public TestsDates(int numbersOfTest) {
        this.logins = new String[numbersOfTest];
        this.passwords = new String[numbersOfTest];

        for (int i = 0; i < numbersOfTest; i++) {
            this.logins[i] = "login" + i;
            this.passwords[i] = "password" + i;
        }
    }

    public String getLogin(int numberTest) {
        int i = numberTest -1;
        if (i < 0 || i >= logins.length) throw new ArrayIndexOutOfBoundsException("end of test logins");
        return this.logins[i];
    }

    public String getPassword(int numberTest) {
        int i = numberTest -1;
        if (i < 0 || i >= passwords.length) throw new ArrayIndexOutOfBoundsException("end of test passwords");
        return this.passwords[i];
    }

    public void setLogin(int numberTest, String login){
        int i = numberTest -1;
        if (i < 0 || i >= logins.length) throw new ArrayIndexOutOfBoundsException("end of test logins");
        this.logins[i] = login;
    }

    public void setPassword(int numberTest, String login){
        int i = numberTest -1;
        if (i < 0 || i >= passwords.length) throw new ArrayIndexOutOfBoundsException("end of test passwords");
        this.passwords[i] = login;
    }


}
