const canvas = document.getElementById("coordinate-system");
const ctx = canvas.getContext("2d");


canvas.width = 300
canvas.height = 300
let w = canvas.width,
    h = canvas.height;

const hatchWidth = 20 / 2;
const hatchGap = 56;

let dataMap = {
    0.0: [],
    1.0: [],
    1.5: [],
    2.0: [],
    2.5: [],
    3.0: [],

};

canvas.addEventListener('click', function (event) {
    var mouseX = (event.clientX - canvas.getBoundingClientRect().left-250)/40;
    var mouseY = -(event.clientY - canvas.getBoundingClientRect().top-250)/40;

    mouseX = mouseX.toFixed(2);
    mouseY = mouseY.toFixed(2);


    var mouseXe = event.clientX - canvas.getBoundingClientRect().left;
    var mouseYe = event.clientY - canvas.getBoundingClientRect().top;

    drawPointe(mouseXe, mouseYe, mouseX, mouseY);

    x = Number.parseFloat(mouseX);
    y = Number.parseFloat(mouseY);
    var form = document.getElementById('form:R');
    r = Number.parseFloat(form.value );

    if(isNaN(r)){
        rInput.value = 0;
        r=0;
    }
    // Проверяем, существует ли массив для этого r
    if (dataMap.hasOwnProperty(r)) {
        // Добавляем данные в массив
        dataMap[r].push({ x: x, y: y, hit: validate(x, y, r) });
    } else {
        // Если массив для r не существует, создаем его и добавляем данные
        dataMap[r] = [{ x: x, y: y, hit: validate(x, y, r) }];
    }
    const oldX = xInput.value, oldY = yInput.value;
    yInput.value = mouseY;
    xInput.value = mouseX;
    submitBtn.onclick(undefined);
    xInput.value = oldX;
    yInput.value = oldY;


});

function drawG(r){
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    canvas.width = 500;
    canvas.height = 500;
    let radiusSpec = 200*r/5;
    const radius = 200;

    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const pointRadius = 3;

    ctx.strokeStyle = "#2e2e2e";
    ctx.lineWidth = 2;
    ctx.fillStyle = "#ffffff";


    ctx.fillStyle = "#FF69B4";
    // sign x axis
    ctx.fillText("X", canvas.width - 90, centerY + 30);
    ctx.fillStyle = "#FF69B4";
    // sign y axis
    ctx.fillText("Y", centerX - 30, 90);


    ctx.fillStyle = 'blue';
    ctx.strokeStyle = '#2e2e2e';
    ctx.lineWidth = 1;

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.arc(centerX, centerY, radiusSpec, 0, 1/2*Math.PI); // Изменение углов
    ctx.fillStyle = "rgba(255, 105, 180, 0.66)";
    ctx.fill();
    ctx.stroke();

    // triangle
    ctx.beginPath();
    ctx.moveTo(centerX - radiusSpec, centerY);
    ctx.lineTo(centerX , centerY  +radiusSpec/2);
    ctx.lineTo(centerX, centerY);
    ctx.fillStyle = "rgba(255, 105, 180, 0.66)";
    ctx.fill();
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(centerX, centerY - radiusSpec);
    ctx.lineTo(centerX + radiusSpec/2, centerY - radiusSpec);
    ctx.lineTo(centerX + radiusSpec/2, centerY);
    ctx.closePath();
    ctx.fillStyle = "rgba(255, 105, 180, 0.66)";
    ctx.fill();
    ctx.stroke();


    ctx.strokeStyle = "#2e2e2e";
    ctx.lineWidth = 2;
    ctx.fillStyle = "#FF69B4";


    // x
    ctx.beginPath();
    ctx.moveTo(90, centerY);
    ctx.lineTo(canvas.width -90, centerY);
    ctx.stroke();

    // x arrow
    ctx.beginPath();
    ctx.moveTo(canvas.width - 90, centerY - 14);
    ctx.lineTo(canvas.width - 70, centerY);
    ctx.lineTo(canvas.width - 90, centerY + 14);
    ctx.fillStyle = '#2e2e2e';
    ctx.fill();

    // x arrow
    ctx.beginPath();
    ctx.moveTo(canvas.width - 90, centerY - 10);
    ctx.lineTo(canvas.width - 75, centerY);
    ctx.lineTo(canvas.width - 90, centerY + 10);
    ctx.fillStyle = '#FF69B4';
    ctx.fill();

    // y axis
    ctx.beginPath();
    ctx.moveTo(centerX, 90);
    ctx.lineTo(centerX, canvas.height-90);
    ctx.stroke();

    // y arrow
    ctx.beginPath();
    ctx.moveTo(centerX - 14, 90);
    ctx.lineTo(centerX, 70);
    ctx.lineTo(centerX + 14, 90);
    ctx.fillStyle = '#2e2e2e';
    ctx.fill();

    // y arrow
    ctx.beginPath();
    ctx.moveTo(centerX - 10, 90);
    ctx.lineTo(centerX, 75);
    ctx.lineTo(centerX + 10, 90);
    ctx.fillStyle = '#FF69B4';
    ctx.fill();

    // ox
    ctx.beginPath();
    // ctx.arc(centerX + radius, centerY, pointRadius, 0, 2 * Math.PI);
    // ctx.arc(centerX + 4/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX + 3/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX + 2/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX + 1/5*radius, centerY, pointRadius, 0, 2 * Math.PI);

    // ctx.arc(centerX - radius, centerY, pointRadius, 0, 2 * Math.PI);
    // ctx.arc(centerX - 4/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX - 3/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX - 2/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX - 1/5*radius, centerY, pointRadius, 0, 2 * Math.PI);
    ctx.fillStyle = '#2e2e2e';
    ctx.fill();

    ctx.font = '12px Arial';
    ctx.fillStyle = '#2e2e2e';

    ctx.fillText('3', centerX + (3/5) * radius + pointRadius, centerY + 20);
    ctx.fillText('2', centerX + (2/5) * radius + pointRadius, centerY + 20);
    ctx.fillText('1', centerX + (1/5) * radius + pointRadius, centerY + 20);

    ctx.fillText('-3', centerX - (3/5) * radius - pointRadius, centerY + 20);
    ctx.fillText('-2', centerX - (2/5) * radius - pointRadius, centerY + 20);
    ctx.fillText('-1', centerX - (1/5) * radius - pointRadius, centerY + 20);

    // точки оу
    ctx.beginPath();
    // ctx.arc(centerX, centerY + radius, pointRadius, 0, 2 * Math.PI);
    // ctx.arc(centerX, centerY + (4/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY + (3/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY + (2/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY + (1/5) * radius, pointRadius, 0, 2 * Math.PI);

    // ctx.arc(centerX, centerY - radius, pointRadius, 0, 2 * Math.PI);
    // ctx.arc(centerX, centerY - (4/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY - (3/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY - (2/5) * radius, pointRadius, 0, 2 * Math.PI);
    ctx.arc(centerX, centerY - (1/5) * radius, pointRadius, 0, 2 * Math.PI);

    ctx.fillStyle = '#2e2e2e';
    ctx.fill();

    ctx.font = '12px Arial';
    ctx.fillStyle = '#2e2e2e';
    ctx.fillText('-3', centerX + 20, centerY + (3/5) * radius);
    ctx.fillText('-2', centerX + 20, centerY + (2/5) * radius);
    ctx.fillText('-1', centerX + 20, centerY + (1/5) * radius);

    ctx.fillText('3', centerX + 20, centerY - (3/5) * radius);
    ctx.fillText('2', centerX + 20, centerY - (2/5) * radius);
    ctx.fillText('1', centerX + 20, centerY - (1/5) * radius);

    if (dataMap.hasOwnProperty(Number.parseFloat(r))) {
        let subArray = dataMap[Number.parseFloat(r)];
        for (var i = 0; i < subArray.length; i++) {
            drawPointe(subArray[i].x*40+250, (-subArray[i].y*40+250), subArray[i].x, subArray[i].y);
        }
    }

}




var pointRadius = 5;
function drawPointe(x, y, xt, yt) {
    xt = Number.parseFloat(xt);
    yt = Number.parseFloat(yt);

    var form = document.getElementById('form:R');
    r = Number.parseFloat(form.value );
    kode = validate(xt, yt, r);
    color = '#2e2e2e';
    colore = '#FF69B4';
    if(kode){
        colore = '#ffffff';
        color = '#FF69B4';
    }

    ctx.beginPath();
    ctx.arc(x, y, pointRadius+1, 0, 2 * Math.PI);
    ctx.fillStyle = colore;
    ctx.fill();

    ctx.beginPath();
    ctx.arc(x, y, pointRadius, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();

    ctx.font = '12px Arial';
    ctx.fillStyle = '#000000';
    ctx.fillText('('+xt+'; '+yt+')', x + 10, y);


}

function validate(x, y, r){
    if(r===0){
        return false;
    }
    if ((x >= 0 && x <= r / 2) && (y >= 0 && y <= r))
        return true;
    else if ((x <= 0 && x >= -r) && (y <= 0 && y >=  -r/2))
        return x>=-r-2*y;
    else if ((x >= 0 && x <= r) && (y <= 0 && y >= -r))
        return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2);
    else
        return false;
}


function clearAttempt(){
    dataMap = {
        0.0: [],
        1.0: [],
        1.5: [],
        2.0: [],
        2.5: [],
        3.0: [],

    };
    drawG(document.getElementById('form:R').value);
}
