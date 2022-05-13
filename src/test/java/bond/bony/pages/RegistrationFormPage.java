package bond.bony.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationFormPage {
    //locators
    SelenideElement firstName = $("#firstName");
    SelenideElement lastName = $("#lastName");
    SelenideElement email = $("#userEmail");
    SelenideElement phoneNumber = $("#userNumber");
    SelenideElement currentAddress = $("#currentAddress");
    SelenideElement gender = $("#genterWrapper");
    SelenideElement hobbie = $("#hobbiesWrapper");
    SelenideElement subjects = $("#subjectsInput");
    SelenideElement userPic = $("#uploadPicture");
    SelenideElement stateCityWrapper = $("#stateCity-wrapper");
    SelenideElement state = $("#state");
    SelenideElement city = $("#city");
    //SelenideElement submitButton = $("#submit");


    //actions
    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        return this;
    }

    public RegistrationFormPage setFirstName(String value){
        firstName.setValue(value);
        return this;
    }
    public RegistrationFormPage setLastName(String value){
        lastName.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value){
        email.setValue(value);

        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value){
        phoneNumber.setValue(value);

        return this;
    }

    public void setDate(String year, String month, String day){
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(byCssSelector(format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", day))).click();

    }

    public RegistrationFormPage setBitrhDay(String year, String month, String day){
        $("#dateOfBirthInput").click();
        setDate(year, month, day);

        return this;
    }

    public RegistrationFormPage setGender(String value){
        gender.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setHobbie(String value){
        hobbie.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setSubject(String value){
        subjects.setValue(value).pressTab();
        return this;
    }

    public RegistrationFormPage uploadUserPic(String value){
        userPic.uploadFromClasspath("img/" + value);
        return this;
    }

    public RegistrationFormPage setAddress(String value){
        currentAddress.setValue(value);

        return this;
    }


    public RegistrationFormPage selectState(String value){
        state.scrollTo().click();
        stateCityWrapper.parent().$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage selectCity(String value){
        city.scrollTo().click();
        stateCityWrapper.parent().$(byText(value)).click();
        return this;
    }

    public void submitRegistration(){
        //executeJavaScript("document.body.style.zoom='65%'");
        //submitButton.scrollTo().click();
        $("#userNumber").pressEnter();
    }

}
