package testcases.buyer.sol;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.framework.Solicitation;

import java.io.IOException;

public class SolicitationFlowTest {

    Solicitation sol;
    SolCreator creator;


    @BeforeClass
    public void setup() throws IOException { }

    @Test(priority = 1)
    public void CreateSolicitationTest() {

            creator = new SolCreator();
            sol = creator.CreateSolicitation("data/solicitation");

            System.out.printf("SOL# %s created.%n", sol.getSolNumber()); // delete me
    }


}
