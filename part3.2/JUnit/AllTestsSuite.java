import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

//class as test suite
@Suite

// Add other testclasses here
@SelectClasses({
        AccountTest.class
        CustomerTest.class
})

public class AllTestsSuite {

}
