<%@ page import="com.nordwestzap.weblab.dao.AttemptRepository" %>
<%@ page import="com.nordwestzap.weblab.model.Attempt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style><%@include file="style.css"%></style>
    <title>JSP - Hello World</title>
</head>
<body onload="drawG(0)">
<div class="card-user-den">
    <div class="header-with-image">
        <p>
            Дениченко Александр Олегович Р3212 В-2243
        </p>
    </div>
</div>
<br>
<canvas id="coordinate-system"></canvas>

<div class="card">
    <form  method="post" class="user-form">
        <table class="card">
            <tr>
                <td>
                    <fieldset>
                        <legend>Choose your R:</legend>
                        <div>
                            <button type="button" class="button_R" onclick="showValue(1)" >1</button>
                            <button type="button" class="button_R" onclick="showValue(2)" >2</button>
                            <button type="button" class="button_R" onclick="showValue(3)" >3</button>
                            <button type="button" class="button_R" onclick="showValue(4)" >4</button>
                            <button type="button" class="button_R" onclick="showValue(5)" >5</button>
    <%--                        <div class="result_R"></div>--%>
                            <input type="hidden" id="param_r" name="r" value="" required>
                        </div>

                    </fieldset>
                </td>
                <td>
                    <fieldset>
                        <legend>Choose your Y:</legend>
                        <div>
                            <input type="text" name="y" id="y"  required>
                        </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <fieldset>
                        <legend>Choose your X:</legend>
                        <div>
                            <input type="checkbox" id="btn-4" class="x" onclick="showValueX(-4)" />
                            <label for="btn-4">-4</label>
                            <input type="checkbox" id="btn-3" class="x" onclick="showValueX(-3)" />
                            <label for="btn-3">-3</label>
                            <input type="checkbox" id="btn-2" class="x" onclick="showValueX(-2)" />
                            <label for="btn-2">-2</label>
                            <input type="checkbox" id="btn-1" class="x" onclick="showValueX(-1)" />
                            <label for="btn-1">-1</label>
                            <input type="checkbox" id="btn0" class="x"  onclick="showValueX(0)" />
                            <label for="btn0">0</label>
                            <input type="checkbox" id="btn1" class="x" onclick="showValueX(1)" />
                            <label for="btn1">1</label>
                            <input type="checkbox" id="btn2" class="x" onclick="showValueX(2)" />
                            <label for="btn2">2</label>
                            <input type="checkbox" id="btn3" class="x"  onclick="showValueX(3)" />
                            <label for="btn3">3</label>
                            <input type="checkbox" id="btn4" class="x"  onclick="showValueX(4)" />
                            <label for="btn4">4</label>
                            <input type="hidden" id="param_x" name="x" value="" required>
                        </div>
                    </fieldset>
                </td>
            </tr>
            <tr>
                <td>
                    <input class="submit-button" type="submit" value="Проверка результата">
                </td>
                <td>
                    <input class="clear_table" type="button" value="Очистить таблицу" onclick="deleteDB()">
                </td>
            </tr>
        </table>
    </form>
</div>
<br>
<h1 class="cool-list">List
    <h1 class="cool-try">try</h1>
</h1>

<table class="table">
    <tr id="result">
<%--        <th>Session ID</th>--%>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Attempt Time</th>
        <th>Script Duration</th>
        <th>Is Hit</th>
    </tr>
    <%
        AttemptRepository attemptRepository = (AttemptRepository) request.getAttribute("Attempt-Repository");
        for (int i = 0; i < attemptRepository.getAttempts().size(); i++) {
            out.println("<tr>"+
//                "<td>"+ attemptRepository.getAttempts().get(i).getSessionId() +"</td>"+
                "<td>"+ attemptRepository.getAttempts().get(i).getX() +"</td>"+
                "<td>"+ attemptRepository.getAttempts().get(i).getY() +"</td>"+
                "<td>"+ attemptRepository.getAttempts().get(i).getR() +"</td>"+
                "<td>"+attemptRepository.getAttempts().get(i).getAttemptTime()+"</td>"+
                "<td>"+attemptRepository.getAttempts().get(i).getScriptDuration()+"</td>"+
                    "<td>"+attemptRepository.getAttempts().get(i).isHit()+"</td>"+
                "</tr>");
        }
    %>
</table>

</body>
<script><%@ include file="index.js"%></script>
<script><%@ include file="decart.js"%></script>
</html>