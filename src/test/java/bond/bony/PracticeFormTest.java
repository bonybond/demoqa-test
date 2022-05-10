package bond.bony;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x820";
        Configuration.webdriverLogsEnabled = true;
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillAllData(){
        String name = "Vasily";
        String lastName = "Pupkin";
        String email = "vasya@pupkin.com";
        String mobileNumber = "89151234567";
        String curAddress = "Avenue Street, 13";


        open("/automation-practice-form");
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        webdriver().driver().actions().moveToElement($("#gender-radio-1")).
                click().build().perform();
        $("#userNumber").setValue(mobileNumber);
        //$(".col-sm-1").findElement(By.id("hobbies-checkbox-1")).click();

        //March, 13th 1999
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__year-select")).selectOptionByValue("1999");
        $(".react-datepicker__month-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__month-select")).selectOptionByValue("2");
        $(".react-datepicker__day--013").click();

        webdriver().driver().actions().moveToElement($("#hobbies-checkbox-1")).
                click().build().perform();
        webdriver().driver().actions().moveToElement($("#hobbies-checkbox-3")).
                click().build().perform();
        $("#subjectsInput").
                setValue("m").pressTab().setValue("ph").pressTab(); //Subjects
        //picture
        $("#uploadPicture").uploadFromClasspath("teddy-bears.jpg");

        executeJavaScript("document.body.style.zoom='65%'");
        $("#currentAddress").setValue(curAddress).pressTab();

        //$("state>input").pressEnter(); //State
        //webdriver().driver().actions().moveToElement($("#submit")).
        //        click().build().perform();
        //$("#submit").scrollTo().click();
        $("#userNumber").pressEnter();

    }

    @AfterAll
    static void finalActions(){
        sleep(3000);
    }

}
