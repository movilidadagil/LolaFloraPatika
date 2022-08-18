import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class RunDocker {

    public static void main(String[] args){
         TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("testng.xml");//path to xml..
        testng.setTestSuites(suites);
        testng.run();
    }
}
