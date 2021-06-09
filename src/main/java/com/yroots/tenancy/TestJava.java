package com.yroots.tenancy;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public class TestJava {

        public static final String REGEX = "[^a-zA-Z]+";
        public static final int ALIAS_LENGTH = 10;
        public static final int ALIAS_CELL_LENGTH = 5;
        public static final int ALIAS_NAME_LENGTH = 5;

        public List<String> getUserAlias(String mobileNo, String custName, String memberId) {

        if (StringUtils.isEmpty(mobileNo) || StringUtils.isEmpty(custName)) {
            log.info("mobile no {} or custName{} is empty", mobileNo, custName);
            return Collections.emptyList();
        }

        String custNameWithAplhabets = custName.replaceAll(REGEX, "").toLowerCase();
        if (custNameWithAplhabets.length() == 0) {
            log.info("Cust Name after replacing with regex is empty for user {}", memberId);
            return Collections.emptyList();
        }
        int randomLength;
        List<String> generatedUserAlias = new ArrayList<>();
        String name;

        if (custNameWithAplhabets.length() < 5) {
            randomLength = ALIAS_LENGTH - custNameWithAplhabets.length();
            name=custNameWithAplhabets;
        } else {
            randomLength = ALIAS_CELL_LENGTH;
            StringBuilder alias = new StringBuilder();
            name=custNameWithAplhabets.substring(0, ALIAS_NAME_LENGTH);
            alias.append(name).
                    append(mobileNo.substring(mobileNo.length() - ALIAS_CELL_LENGTH, mobileNo.length()));

            generatedUserAlias.add(alias.toString());
        }

        generateUserAliasListWithRandomDigits(generatedUserAlias, name, randomLength);
        return generatedUserAlias;


    }


        public List<String> generateUserAliasListWithRandomDigits(List<String> generatedUserAlias,
            String custName, int digits) {
        int min = (int) Math.pow(10, digits - 1);
        Set<Integer> uniqueRandomNumber = new HashSet<>();

        while (uniqueRandomNumber.size() <= 30) {
            int randomNumber = min + new Random().nextInt(9 * min);
            uniqueRandomNumber.add(randomNumber);
        }

        Iterator<Integer> iterator = uniqueRandomNumber.iterator();
        while (iterator.hasNext()) {
            generatedUserAlias.add(custName.concat(String.valueOf(iterator.next())));
        }


        return generatedUserAlias;
    }

    public static void main(String[] args) {
        TestJava tes=new TestJava();
        System.out.println(tes.getUserAlias("8406074111","KUMAR  VAIBHAV","8406074111@nocash.mobikwik.com"));
    }
}
