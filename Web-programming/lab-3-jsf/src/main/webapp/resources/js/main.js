window.onload = function() {
    drawG(document.getElementById('form:R').value);
}

let xValid = true, yValid = true, rValid = true;


const messages = document.getElementById('messages');


// X btn validation
let xInput = document.getElementById('form:X');
const xBtnInput = document.getElementById('xBtn');
const xBtnInputs = xBtnInput.querySelectorAll('button');
xBtnInputs.forEach(button => {
    button.addEventListener('click', () => {
        xInput.value = button.textContent;
        xValid = true;
        toggleSubmitBtn();
    })
});


// // Y input field validation
const yInput = document.getElementById('form:Y');
yInput.addEventListener('input', () => {
    yValid = (yInput.value.length > 0)&&(Number.parseFloat(yInput.value)>=-5.0)&&(Number.parseFloat(yInput.value)<=3.0);
    toggleSubmitBtn();
})
//
// R buttons validation
let rInput = document.getElementById('form:R');
const rRadioInput = document.getElementById('rRadio');
const rRadioInputs = rRadioInput.querySelectorAll('input');
rRadioInputs.forEach(radio => {
    radio.addEventListener('input', () => {
        rInput.value = radio.value;
        rValid = true;
        drawG(rInput.value);
        toggleSubmitBtn();
    })
});

const submitBtn = document.getElementById('form:submitBtn');
submitBtn.addEventListener('click', () => {
    let r = Number.parseFloat(rInput.value),
        x = Number.parseFloat(xInput.value),
        y = Number.parseFloat(yInput.value);

    if(isNaN(r)){
        r=0;
    }
    if (dataMap.hasOwnProperty(r)) {
        dataMap[r].push({ x: x, y: y, hit: validate(x, y, r) });
    } else {
        dataMap[r] = [{ x: x, y: y, hit: validate(x, y, r) }];
    }
    drawPointe(xInput.value*40+250, (-yInput.value*40+250), xInput.value, yInput.value);
});
function toggleSubmitBtn() {
    // check X, Y, R validity
    submitBtn.disabled = !(xValid && yValid && rValid)
}

// xInput.value = '';
// yInput.value = '';
// rInput.value = '';
// toggleSubmitBtn()