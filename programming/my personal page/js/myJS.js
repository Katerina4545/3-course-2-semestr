const menu = document.getElementsByClassName("block")[0];
const button = document.getElementsByClassName("menu")[0];

button.onclick = () => {
    console.log(menu.style)

    if(menu.style.display == "none"|| menu.style.display == "")  
        menu.style.display = "block"; 
    else if(menu.style.display == "block")  
        menu.style.display = "none";     
    }


const phone = document.getElementsByClassName("phone")[0];
const email = document.getElementsByClassName("email")[0];

const phoneBlock = document.getElementsByClassName("phoneBlock")[0];
const emailBlock = document.getElementsByClassName("emailBlock")[0];

phone.onclick = () => {
    let stylePhone = phoneBlock.style.display;
    let styleEmail = emailBlock.style.display;
    
    if(stylePhone == "none" || stylePhone == "")
    {
        emailBlock.style.display = "none";
        phoneBlock.style.display = "block";
    }

    else if(stylePhone == "block")
    {        
        phoneBlock.style.display = "none";
    }
}

email.onclick = () => {
    let stylePhone = phoneBlock.style.display;
    let styleEmail = emailBlock.style.display;
    
    if(styleEmail == "none" || styleEmail == "")
    {
        phoneBlock.style.display = "none";
        emailBlock.style.display = "block";
    }

    else if(styleEmail == "block")
    {        
        emailBlock.style.display = "none";
    }
}

