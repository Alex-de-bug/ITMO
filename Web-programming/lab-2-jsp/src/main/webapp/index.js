function deleteDB(){
    fetch("lab", {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json" // Настройка заголовка, если необходимо
        }
    })
        .then(function(response) {
            if (response.ok) {
                // Получите элемент с id "result"
                var resultTable = document.getElementById("result");
                while (resultTable.nextElementSibling) {
                    resultTable.nextElementSibling.remove();
                }
                console.log("Запрос DELETE успешно выполнен.");
                drawG(0);
            } else {
                console.error("Произошла ошибка при выполнении запроса DELETE.");
            }
        })
        .catch(function(error) {
            console.error("Произошла ошибка при выполнении запроса DELETE:", error);
        });
}

function showValue(value) {
    var form = document.querySelector("#param_r");
    form.value = value;
    drawG(Number.parseInt(value));
}
function showValueX(value) {
    var form = document.querySelector("#param_x");
    form.value = value;
    inputs=document.getElementsByClassName("x");
    for(var i=0;i<inputs.length;i++) {
        inputs[i].onchange=function() {
            for(var i=0;i<inputs.length;i++)
            {
                if(inputs[i].type=="checkbox")
                {
                    inputs[i].checked=false;
                }
                this.checked=true;
            }
        }
    }
}

const form = document.querySelector('.user-form');
form.addEventListener('submit', (evt) => {
    // Отменяем действие по умолчанию
    evt.preventDefault();
    let x = document.querySelector("#param_x").value;
    let y = document.querySelector("#y").value;
    let r = document.querySelector("#param_r").value;

    if (x==="") {
        alert('Choose X');
        evt.preventDefault();
        return;
    }
    if (y==="") {
        alert('Choose Y');
        return;
    }
    if (r==="") {
        alert('Choose R');
        return;
    }

    if (isNaN(Number.parseFloat(y)) || Number.parseFloat(y) < -3 || Number.parseFloat(y) > 5) {
        alert('Enter number from -3 to 5');
        return;
    }
    drawPointe(x*40+250, (-y*40+250), x, y)
    sendDataPoint(x, y, r);
});


function sendDataPoint(x, y, r){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "lab", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var resultContainer = document.getElementById("result");
            var jsonResponse = JSON.parse(xhr.responseText);
            var attemptTime = jsonResponse.attemptTime.toString();
            var scriptDuration = jsonResponse.scriptDuration.toString();
            var hit = jsonResponse.hit.toString();
            resultContainer.insertAdjacentHTML("afterend",
                "<tr>"+
                "<td>"+jsonResponse.x+"</td>"+
                "<td>"+jsonResponse.y+"</td>"+
                "<td>"+jsonResponse.r+"</td>"+
                "<td>"+attemptTime +"</td>"+
                "<td>"+scriptDuration +"</td>"+
                "<td>"+hit +"</td>"+
                "</tr>"
            );
        }
    };

    var data = "x=" + encodeURIComponent(x) + "&y=" + encodeURIComponent(y) + "&r=" + encodeURIComponent(r);
    xhr.send(data);
}