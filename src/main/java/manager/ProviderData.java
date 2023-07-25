package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ProviderData {


    @DataProvider
    public Iterator<Object[]> regData_CSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/regData.csv")));

        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{User.builder()
                    .email(split[0])
                    .password(split[1])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();

    }



}
