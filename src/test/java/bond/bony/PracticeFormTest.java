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
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillAllData(){
        String name = "Vasily";
        String lastName = "Pupkin";
        String email = "vasya@pupkin.com";
        String mobileNumber = "+79151234567";

        open("/automation-practice-form");
        $("#firstName").setValue(name);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#userNumber").setValue(mobileNumber);
        //$("[name=gender][value=Male]").click(); // GENDER
        //$("#gender-radio-1").click(); // GENDER

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__year-select")).selectOptionByValue("1999"); //choose year
        $(".react-datepicker__month-dropdown-container--select").click();
        $(By.cssSelector(".react-datepicker__month-select")).selectOptionByValue("2"); //choose month
        $(".react-datepicker__day--013").click();

        //$("#userEmail").setValue(email); //Subjects
        //$("#userEmail").setValue(email); //picture
        //Address
        //State
        //City

    }

    @AfterAll
    static void finalActions(){
        sleep(10000);
    }

}
