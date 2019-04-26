package testcases.buyer.contract;

import framework.Contract;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContractTest {

    Contract contract;

    @BeforeClass
    public void setup() {
        contract = new Contract();
    }

    @Test
    public void CreateContractTest() {

        ContractCreator creator = new ContractCreator();
        contract = creator.CreateContract("data/contract");

        System.out.format("Contract %s created.%n", contract.getContractNumber());
    }

}
