package bond.bony.tests;

import bond.bony.pages.RegistrationFormPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static bond.bony.utils.RandomUtils.getRandomInt;
import static bond.bony.utils.RandomUtils.getRandomMonth;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

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
                birthDay = format("%02d", getRandomInt(1,28)),
                birthMonth = getRandomMonth(),
                birthYear = String.valueOf(getRandomInt(1995,2010)),
                state = "NCR",
                city = "Delhi";
        String[] hobbies = {"Sports", "Music"};
        String[] subjects = {"Maths", "Physics"};

        String pictureFile = "teddy-bears.jpg";


        registrationFormPage.openPage()
                .setFirstName(name)
                .setLastName(lastName)
                .setGender(gender)
                .setEmail(email)
                .setPhoneNumber(mobileNumber)
                .setBitrhDay(birthYear, birthMonth, birthDay)
                .setHobbie(hobbies[0]).setHobbie(hobbies[1])
                .setSubject(subjects[0].substring(0,2)).setSubject(subjects[1].substring(0,4))
                .uploadUserPic(pictureFile)
                .setAddress(curAddress)
                .selectState(state)
                .selectCity(city)
                .submitRegistration();





        //Assertions
        SelenideElement resultTable = $(".table");
        $("#example-modal-sizes-title-lg").
                shouldHave(Condition.text("Thanks for submitting the form"));

        resultTable.$(byText("Student Name")).parent()
                .shouldHave(Condition.text(format("%s %s", name, lastName)));
        resultTable.$(byText("Student Email")).parent()
                .shouldHave(Condition.text(email));
        resultTable.$(byText("Gender")).parent()
                .shouldHave(Condition.text(gender));
        resultTable.$(byText("Mobile")).parent()
                .shouldHave(Condition.text(mobileNumber));
        resultTable.$(byText("Date of Birth")).parent()
                .shouldHave(Condition.text(format("%s %s,%s", birthDay, birthMonth, birthYear)));
        //Subj
        resultTable.$(byText("Subjects")).parent()
                .shouldHave(Condition.text(format("%s, %s", subjects[0], subjects[1])));

        resultTable.$(byText("Hobbies")).parent()
                .shouldHave(Condition.text(format("%s, %s", hobbies[0], hobbies[1])));
        resultTable.$(byText("Picture")).parent()
                .shouldHave(Condition.text(pictureFile));
        resultTable.$(byText("Address")).parent()
                .shouldHave(Condition.text(curAddress));
        resultTable.$(byText("State and City")).parent()
                .shouldHave(Condition.text(format("%s %s", state, city)));
    }

    @AfterAll
    static void finalActions(){
        sleep(3000);
    }

}
