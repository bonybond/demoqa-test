package bond.bony;

import com.codeborne.selenide.Condition;
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
        Configuration.browserSize = "1920x1080";
        Configuration.webdriverLogsEnabled = true;
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillAllData(){
        String name = "Vasily";
        String lastName = "Pupkin";
        String email = "vasya@pupkin.com";
        String mobileNumber = "9151234567";
        String curAddress = "Avenue Street, 13";
        String gender = "Male";
        String genderN = "1";
        String birthDay = "13";
        String birthMonth = "March";
        String birthMonthN = "2";
        String birthYear = "1999";
        String pictureFile = "teddy-bears.jpg";


        open("/automation-practice-form");
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        webdriver().driver().actions().moveToElement($("#gender-radio-"+genderN)).
                click().build().perform();
        $("#userNumber").setValue(mobileNumber);

        //March, 13th 1999
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__year-select")).selectOptionByValue(birthYear);
        $(".react-datepicker__month-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__month-select")).selectOptionByValue(birthMonthN);
        $(".react-datepicker__day--0"+birthDay).click();

        webdriver().driver().actions().moveToElement($("#hobbies-checkbox-1")).
                click().build().perform();
        webdriver().driver().actions().moveToElement($("#hobbies-checkbox-3")).
                click().build().perform();

        $("#subjectsInput").
                setValue("m").pressTab().setValue("ph").pressTab(); //Subjects
        //picture
        $("#uploadPicture").uploadFromClasspath(pictureFile);

        executeJavaScript("document.body.style.zoom='65%'");
        $("#currentAddress").setValue(curAddress).pressTab();

        //$("state>input").pressEnter(); //State
        //City

        //webdriver().driver().actions().moveToElement($("#submit")).
        //        click().build().perform();
        //$("#submit").scrollTo().click();
        $("#userNumber").pressEnter();



        //Assertions
        $("#example-modal-sizes-title-lg").
                shouldHave(Condition.text("Thanks for submitting the form"));
        $("table").
                shouldHave(Condition.text(name + " " + lastName)).
                shouldHave(Condition.text(email)).
                shouldHave(Condition.text(gender)).
                shouldHave(Condition.text(birthDay + " " + birthMonth + "," + birthYear)).
                shouldHave(Condition.text(mobileNumber)).
                shouldHave(Condition.text(pictureFile)).
                shouldHave(Condition.text(curAddress));
    }

    @AfterAll
    static void finalActions(){
        sleep(3000);
    }

}
