package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageFactory.PositiveNegativeListPage_PF;

public class positiveEntrySteps {

     WebDriver driver;
     PositiveNegativeListPage_PF pnl_pf ;


    @When("user on dashboard page")
    public void user_on_dashboard_page() throws InterruptedException {
        System.out.println("=========positiveEntrySteps===========");
        driver = VtLoginSteps_PF.getDriver();
       // System.out.println("Title:...."+driver.getTitle());
        pnl_pf = new PositiveNegativeListPage_PF(driver);
    }


    @When("click on Risk Management")
    public void click_on_risk_management() throws InterruptedException {
        pnl_pf.clickOnRiskManagementTab();
    }
    @When("click on configurations")
    public void click_on_configurations() throws InterruptedException {
        pnl_pf.clickOnConfigurationsButton();
    }
    @When("click on Positive Negative List")
    public void click_on_positive_negative_list() throws InterruptedException {
        pnl_pf.clickOnPositiveNegativeButton();

    }
    @When("user on Configurations Page")
    public void user_on_configurations_page() throws InterruptedException {

        pnl_pf.clickStickyArrow();
    }
    @When("click on Add Positive Entry")
    public void click_on_add_positive_entry() throws InterruptedException {
        pnl_pf.clickOnAddPositiveEntry();
    }
    @When("open Add New Entry Form")
    public void open_add_new_entry_form() {

    }
    @When("user Fill Add New Entry Form")
    public void user_fill_add_new_entry_form() throws InterruptedException {
        System.out.println("fill form data....");
        pnl_pf.setData_Add_New_Entry_Form("Email", "test_positive_2@xyz.in", "vesta","08-31-2021","This is for test");
    }
    @When("click on Add New Entry Create Button")
    public void click_on_add_new_entry_create_button() throws InterruptedException {
        pnl_pf.clickOnCreateButton();
    }
    @Then("user is navigated to Configurations page")
    public void user_is_navigated_to_configurations_page() throws InterruptedException {
        pnl_pf.clickOnSearchInput("test_positive_2@xyz.in");
    }
}
