package com.generic.functional.automation.ui.tests.home;

import com.aventstack.extentreports.Status;
import com.generic.framework.ui.helper.FileHelper;
import com.generic.framework.ui.helper.HighlightHelper;
import com.generic.framework.ui.helper.QueryChecker;
import com.generic.functional.automation.ui.tests.common.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class QueriesAutomation extends TestConfig {

    QueryChecker queryChecker;
    HighlightHelper highlightHelper;
    FileHelper fileHelper;
    final int time_to_wait = 30;
    WebDriverWait wait;

    @BeforeMethod
    public void beforeMethod() {
        wait = new WebDriverWait(driver, 15);
        queryChecker = new QueryChecker();
        highlightHelper = new HighlightHelper();
        fileHelper = new FileHelper();
    }

    /**
     * testDocumentsQueries is used to run and verify document related queries in search bubble
     *
     * @author Teja
     */

    @Test(groups = {"smokeTest"})
    public void testDocumentsQueries() {
        test = extent.createTest("Verify Documents Queries");
        try {
            login.doLogin(test);

            queryChecker.runSearchBubbleQuery(driver, "list all documents", test);
            test.createNode("list all Documents verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all documents where provider is FedEx", test);
            test.createNode("list all documents where provider is FedEx verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all documents where delivery number ends with 003", test);
            test.createNode("list all documents where delivery number ends with 003 verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all documents where document type is LBL ", test);
            test.createNode("list all documents where document type is LBL verified successfully");

            test.createNode("Verified Documents Queries Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * testFreightChargeQueries is used to run some queries in search bubble
     *
     * @author Haritha
     */
    @Test (groups = {"smokeTest"})
    public void testFreightChargeQueries() {
        //Setup

        test = extent.createTest("Verify Freight Charges Queries");
        try {
            driver.manage().timeouts().implicitlyWait(time_to_wait, TimeUnit.SECONDS);
            login.doLogin(test);
            test.log(Status.INFO, "Clicking on Search Bar");
            //query 1
            queryChecker.runSearchBubbleQuery(driver, "list all charges", test);
            test.createNode("list all Freight Charges verified successfully");
            //query 2
            queryChecker.runSearchBubbleQuery(driver, "list all charges where carrier id is fedex", test);
            test.createNode("list all charges where carrier id is fedex verified successfully");
            //query 3
            queryChecker.runSearchBubbleQuery(driver, "list all charges where charge type is Disk and freight charges amount > 100", test);
            test.createNode("list all charges where charge type is Disk and freight charges amount > 100 verified successfully");
            //query 4
            queryChecker.runSearchBubbleQuery(driver, "list all charges where carrier id is ups frieght", test);
            test.createNode("list all charges where carrier id is ups frieght verified successfully");
            test.createNode("Verified Freight Charge Queries Successfully!");

        } catch (Exception e) {
            test.createNode("Exception (" + e.toString() + ") found").fail(e);

        }

    }

    /**
     * testFreightChargeQueriesViaFile is used to run freight charge queries in search bubble read from a txt file
     *
     * @author Sai Tanuj
     */
    @Test (groups = {"smokeTest"})
    public void testFreightChargeQueriesViaFile() {
        //Setup
        test = extent.createTest("Verify Freight Charges Queries via Query File Input");
        driver.manage().timeouts().implicitlyWait(time_to_wait, TimeUnit.SECONDS);
        String myPersonalPath = System.getProperty("user.dir");
        //The .txt file would be different for other test cases, but everything else would be the same (unless you work in Data Studio)
        String filePath = myPersonalPath + "/src/test/java/com/generic/functional/automation/ui/tests/home/FreightChargeQueries.txt";
        List<String> queries = fileHelper.retrieveArrayList(filePath);
        try {
            login.doLogin(test);
            test.log(Status.INFO, "Clicking on Search Bar");
            //For loop obtains queries from FreightChargeQueries.txt
            for(int i = 0; i < queries.size(); i++) {
                System.out.println("\n" + queries.get(i) + "\n");
                queryChecker.runSearchBubbleQuery(driver, queries.get(i), test);
                test.createNode(queries.get(i) + " verified successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.createNode("Verified Freight Charge Queries Successfully!");
    }

    /**
     * testDocumentBydocTypeQueries is used to run some queries in search bubble.
     *
     * @author Pardhu
     */
    @Test (groups = {"smokeTest"})
    public void testDocumentBydocTypeQueries() {
        test = extent.createTest("Verify DocumentByDocTypeQueries");
        try {
            login.doLogin(test);
            queryChecker.runSearchBubbleQuery(driver, "list all Document ", test);
            test.createNode("list all Documents verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all Document where Document Type is RPT", test);
            test.createNode("list all Documents where Document Type is RPT verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all Document where Document Type is FRM", test);
            test.createNode("list all Documents where Document Type is FRM verified successfully");
            queryChecker.runSearchBubbleQuery(driver, "list all Document where Document Type is LBL", test);
            test.createNode("list all Documents where Document Type is LBL verified successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * testShipmentsByShipTypeQueries  is used to automate some queries search bubble about Shipments Table(verify its existence)
     *
     * @author priyanka
     */
    @Test
    public void testShipmentsByShipTypeQueries() {
        test = extent.createTest("Verify ShipmentsByShipTypeQueries");
        try {
            login.doLogin(test);
            //query 1
            queryChecker.runSearchBubbleQuery(driver, "list all Shipments ", test);
            test.createNode("list all Shipments verified successfully");
            //query 2
            queryChecker.runSearchBubbleQuery(driver, "list of shipments where shipment number is 24691", test);
            test.createNode("list of shipments where shipment number is 24691 verified successfully");
            //query 3
            queryChecker.runSearchBubbleQuery(driver, "list all shipments where plant is 2100", test);
            test.createNode("list all shipments where plant is 2100 verified successfully");
            //query 4
            queryChecker.runSearchBubbleQuery(driver, "list all shipments where delivery number start with pwsr", test);
            test.createNode("list all shipments where delivery number start with pwsr verified successfully");
            test.createNode("Verified Shipments  Queries Successfully!");

        } catch (Exception e) {
            test.createNode("Exception (" + e.toString() + ") found").fail(e);
        }
    }

}
