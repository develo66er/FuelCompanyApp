<%@page import="com.models.Dispenser"%>
<%@page import="com.models.PetrolStation"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.*"%>

<%@page import="com.models.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>petrolstations</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet"  href="./styles/style.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%

            List<Dispenser> dispensers = (List<Dispenser>) request.getAttribute("dispensers");
            List<PetrolStation> petrolStations = (List<PetrolStation>) request.getAttribute("petrolStations");
            String activeFilter = (String) request.getAttribute("activeFilter");
        %>
        <div class="container">
            <div class="row">

                <div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-12">
                            <h4>Фильтровать:</h4>
                            <select onchange="document.location.href = '/FuelCompany/dispenser?id=' + this.value;">>
                                <option value="-1" ${activeFilter==-1?' selected':''}>Все раздаточные колонки</option>
                                <c:forEach  items="${petrolStations}" var="petrolStationFilter">
                                    <option value="${petrolStationFilter.getId()}" ${petrolStationFilter.getId()==activeFilter?' selected':''}>${petrolStationFilter.getAddress()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 dispenser-items">

                            <c:forEach items="${dispensers}" var="dispenser">
                                <div class="dispensers">
                                    <p class="type">Тип : <span>${dispenser.getType()}</span></p>
                                    <p>Количество : <span>${dispenser.getCount()}</span></p>
                                    <p>Модель: <span>${dispenser.getModel()}</span></p>
                                    <p class="station">Адрес заправочной станции : <span>${petrolStations.stream().filter(ps->ps.getId()==dispenser.getPetrolstationId()).findFirst().orElseGet(()->null).getAddress()}</span></p>
                                </div>
                                <a href="/FuelCompany/dispenser?id=${dispenser.getPetrolstationId()}&&delete=${dispenser.getId()}">Удалить</a>
                                <c:choose>
                                    <c:when test="${dispenserId!=null && dispenser.getId()==dispenserId}">

                                        <form name="update-dispenser" action="/FuelCompany/dispenser" method="POST" onsubmit="return updateDispenserChecker()"><br/>
                                            <input type="hidden" name="id" value="${dispenser.getId()}"/><br/>
                                            <input type="hidden" name="old_petrolstation_id" value="${dispenser.getPetrolstationId()}"/><br/>

                                            <label for="dispenser_type">Тип: </label><br/>
                                            <input type="text" name="dispenser_type" value="${dispenser.getType()}"/><br/>
                                            <label for="dispenser_count">Количество: </label><br/>
                                            <input type="number" name="dispenser_count" min="0" step="1" value="${dispenser.getCount()}"/><br/>
                                            <label for="dispenser_model">Модель: </label><br/>
                                            <input type="text" name="dispenser_model" value="${dispenser.getModel()}"/><br/>
                                            <label for="petrolstation_id">Заправочная станция: </label><br/>
                                            <select name="petrolstation_id">
                                                <c:forEach items="${petrolStations}" var="petrolStation">
                                                    <option value="${petrolStation.getId()}" ${petrolStation.getId()==dispenser.getPetrolstationId()?'selected':''}>${petrolStation.getAddress()}</option>
                                                </c:forEach>
                                            </select><br/><br/>
                                            <input type="submit" value="Изменить"/><br/>
                                        </form>

                                        <p id="dispenser-update-error"></p>
                                    </c:when>    
                                    <c:otherwise>
                                        <a href="/FuelCompany/dispenser?id=${dispenser.getPetrolstationId()}&&update=${dispenser.getId()}" onclick="document.update_form.dispenser_type.focus()">Редактировать</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>

                    </div>

                </div>
                <div class="col-sm-6 add-dispenser-form">

                    <h4>Добавить раздаточную колонку</h4><br/>
                    <form  action="/FuelCompany/dispenser" method="POST" name="add-dispenser" onsubmit="return addDispenserChecker()"><br/>
                        <label for="dispenser_type">Тип: </label><br/>
                        <input type="text" name="dispenser_type" placeholder="тип"/><br/>
                        <label for="dispenser_count">Количество: </label><br/>
                        <input type="number" name="dispenser_count" min="0" step="1" value="0"/><br/>
                        <label for="dispenser_model">Модель: </label><br/>
                        <input type="text" name="dispenser_model" placeholder="модель"/><br/>
                        <label for="petrolstation_id">Заправочная станция: </label><br/>
                        <select name="petrolstation_id">
                            <c:forEach items="${petrolStations}" var="petrolStation">
                                <option value="${petrolStation.getId()}">${petrolStation.getAddress()}</option>
                            </c:forEach>
                        </select><br/><br/>
                        <input type="submit" value="Добавить"/><br/>
                    </form>
                    <p id="dispenser-add-error"></p>

                </div>
            </div>


        </div>
    </body>
    <script>

    </script>
</html>
