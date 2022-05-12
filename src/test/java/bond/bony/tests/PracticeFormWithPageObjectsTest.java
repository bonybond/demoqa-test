package bond.bony.tests;

import bond.bony.pages.RegistrationFormPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static bond.bony.utils.RandomUtils.getRandomInt;
import static bond.bony.utils.RandomUtils.getRandomMonth;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormWithPageObjectsTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillAllData(){
        Faker faker =  new Faker();

        String name = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                mobileNumber = "9150123456",
                curAddress = faker.address().fullAddress(),
                gender = "Male",
                birthDay = String.format("%02d", getRandomInt(1,28)),
                birthMonth = getRandomMonth(),
                birthYear = String.valueOf(getRandomInt(1995,2010));

        String pictureFile = "teddy-bears.jpg";


        registrationFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setGender(gender)
                .setEmail(email)
                .setPhoneNumber(mobileNumber)
                .setBitrhDay(birthYear, birthMonth, birthDay)
                .setHobbie("Sports").setHobbie("Music")
                .setSubject("mat").setSubject("ph")
                .uploadUserPic(pictureFile)
                .setAddress(curAddress);

        executeJavaScript("document.body.style.zoom='65%'");
//        registrationFormPage
//                .selectState("NCR")
//                .selectCity("Delhi");
//        sleep(2000);


        //executeJavaScript("document.body.style.zoom='65%'");
        //$("state>input").pressEnter(); //State
        //City
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
