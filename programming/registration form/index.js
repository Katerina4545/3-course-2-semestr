var firstNameValid = false;
var secondNameValid = false;
var emailValid = false;
var passwordValid = false;
var repeatValid = false;
var dateValid = false;


//проверка на валидность имени и фамилии
var firstName = document.getElementsByClassName("name")[0];
var secondName = document.getElementsByClassName("name")[1];
var error = document.getElementsByClassName("error")[0];

firstName.addEventListener('blur', function(){ 
    
    if(/^[А-Яа-яA-Za-z]+$/.test(firstName.value))
    {
        firstNameValid = true;
        firstName.style.boxShadow = '0px 0px 5px 1px #7cb167';
        error.style.display = "none";
    }
    else
    {
        error.textContent = "имя не должно содержать цифр и спец. символов";
        error.style.display = "block";
        firstName.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }    

});

secondName.addEventListener('blur', function(){    

    if(/^[А-Яа-яA-Za-z]+$/.test(secondName.value))
    {
        secondNameValid = true;
        secondName.style.boxShadow = '0px 0px 5px 1px #7cb167';
        error.style.display = "none";
    }
    else
    {
        error.textContent = "фамилия не должна содержать цифр и спец. символов";
        error.style.display = "block";
        secondName.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }

});

var email = document.getElementsByClassName("email")[0];
email.addEventListener("blur", function(){
    if(/[A-Za-z]+@[A-Za-z]+\.[A-Za-z]+/.test(email.value))
    {
        emailValid = true;
        email.style.boxShadow = '0px 0px 5px 1px #7cb167';
        error.style.display = "none";
    }
    else{
        error.textContent = "введите ваш email";
        error.style.display = "block";
        email.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }
}) 


//проверка на валидность пароля и подтверждения пароля

var regex = /(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}/;
var passwordValue;
var passwordError = document.getElementsByClassName("password-error")[0];
var password = document.getElementsByClassName("password")[0];

password.addEventListener("blur", function(){
    if(regex.test(password.value))
    {
        passwordValid = true;
        passwordValue = password.value;
        password.style.boxShadow = '0px 0px 5px 1px #7cb167';
        passwordError.style.display = "none";
    }
    else
    {
        passwordError.textContent = "минимальная длина пароля 8 символов, пароль должен содержать хотя бы одну заглавную и одну строчную буквы, хотя бы одну цифру и один спец символ !@#$%^&*";
        passwordError.style.display = "block";
        password.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }
});

var repeat = document.getElementsByClassName("repeat-password")[0];

repeat.addEventListener("blur", function(){
    if(repeat.value != passwordValue)
    {
        error.textContent = "пароли не совпадают";
        error.style.display = "block";
        repeat.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }
    else
    {
        repeatValid = true;
        repeat.style.boxShadow = '0px 0px 5px 1px #7cb167';
        error.style.display = "none";
    }
});

//проверка на валидность даты рождения и возраста

var date = document.getElementsByClassName("date")[0];
var regex2 = /^[0-9]{0,1}[0-9]{1}\.[0-9]{1}[0-9]{1}\.[1-9]{1}[0-9]{1}[0-9]{1}[0-9]{1}$/;
date.addEventListener("blur", function(){
    if(regex2.test(date.value))
    {
        var array = date.value.split('.');
        var age = 2019 - Number(array[2]);;
        
        if(age < 18)
        {
            error.textContent = "для регистрации вы должны быть старше 18 лет";
            error.style.display = "block";
            date.style.boxShadow = '0px 0px 5px 1px #d37f6a';
        }
        else
        {
            dateValid = true;
            date.style.boxShadow = '0px 0px 5px 1px #7cb167';
            error.style.display = "none";
        }
    }
    else
    {
        error.textContent = "введите данные в формате 01.01.2000";
        error.style.display = "block";
        date.style.boxShadow = '0px 0px 5px 1px #d37f6a';
    }
});

//кнопка отправить
var button = document.getElementsByClassName("button")[0];

button.addEventListener('click', function (){
    if(firstNameValid & secondNameValid 
        & emailValid & passwordValid
        & repeatValid & dateValid)
    {
        location.href = './ready.html';    
    }
    else
    {
        error.textContent = "введите данные все данные корректно";
        error.style.display = "block";
    }
    
})

