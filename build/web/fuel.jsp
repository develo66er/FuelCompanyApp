<%@page import="com.models.Fuel"%>
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

            List<Fuel> fuels = (List<Fuel>) request.getAttribute("fuels");
            List<PetrolStation> petrolStations = (List<PetrolStation>) request.getAttribute("petrolStations");
            String activeFilterStation = (String) request.getAttribute("activeFilterStation");
            if (activeFilterStation == null) {
                activeFilterStation = String.valueOf(-1);
            }

        %>
        <div class="container">
            <div class="row">

                <div class="col-sm-6">
                    <div class="row">

                        <div class="col-sm-12">
                            <h4>Фильтровать по заправочной станции</h4>
                            <select onchange="document.location.href = '/FuelCompany/fuel?id=' + this.value;">>
                                <option value="-1" ${activeFilterStation==-1?' selected':''}>Все заправочные станции</option>
                                <c:forEach  items="${petrolStations}" var="petrolStationOption">
                                    <option value="${petrolStationOption.getId()}" ${petrolStationOption.getId()==activeFilterStation?' selected':''}>${petrolStationOption.getAddress()}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-12 info">
                            <c:forEach items="${fuels}" var="fuel">
                                <div class="row"><div class="col-sm-12">
                                        <div class="row"><div class="col-sm-12 type"><p>Тип : <span>${fuel.getType()}</span></p></div></div>
                                        <div class="row"><div class="col-sm-12"><p>Название: <span>${fuel.getName()}</span></p></div></div>
                                        <div class="row"><div class="col-sm-12"><p>Цена: <span>${fuel.getPrice()}</span></p></div></div>
                                        <div class="row"><div class="col-sm-12"><p>Адрес заправочной станции : <span>${petrolStations.stream().filter(ps->ps.getId()==fuel.getPetrolstationId()).findFirst().orElseGet(()->null).getAddress()}</span></p>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row"><div class="col-sm-12"><a href="/FuelCompany/fuel?id=${fuel.getPetrolstationId()}&&delete=${fuel.getId()}">Удалить</a><br/></div></div>
                                <div class="row"><div class="col-sm-12">
                                        <c:choose>
                                            <c:when test="${fuelId!=null && fuel.getId()==fuelId}">

                                                <form name="update-fuel" action="/FuelCompany/fuel" method="POST" onsubmit="return updateFuelChecker()"><br/>
                                                    <input type="hidden" name="id" value="${fuel.getId()}"/><br/>
                                                    <input type="hidden" name="old_petrolstation_id" value="${fuel.getPetrolstationId()}"/><br/>

                                                    <label for="fuel_type">Тип: </label><br/>
                                                    <input type="text" name="fuel_type" value="${fuel.getType()}"/><br/>
                                                    <label for="fuel_name">Название: </label><br/>
                                                    <input type="text" name="fuel_name" value="${fuel.getName()}"/><br/>
                                                    <label for="fuel_price">Цена: </label><br/>
                                                    <input type="number" name="fuel_price" min="0.00" step="0.01" value="0.00"/><br/>
                                                    <label for="petrolstation_id">Заправочная станция: </label><br/>
                                                    <select name="petrolstation_id">
                                                        <c:forEach items="${petrolStations}" var="petrolStation">
                                                            <option value="${petrolStation.getId()}" ${petrolStation.getId()==fuel.getPetrolstationId()?'selected':''}>${petrolStation.getAddress()}</option>
                                                        </c:forEach>
                                                    </select><br/><br/>
                                                    <input type="submit" value="Изменить"/><br/>
                                                    <p id="update-error"></p>

                                                </form>
                                            </c:when>    
                                            <c:otherwise>
                                                <a href="/FuelCompany/fuel?id=${fuel.getPetrolstationId()}&&update=${fuel.getId()}">Редактировать</a><br/>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="row"><div class="col-sm-12"><br/></div></div>

                        </div>

                    </div>

                </div>
                <div class="col-sm-6">

                    <h4>Добавить топливо</h4><br/>
                    <form  name="add-fuel" class="add-form" action="/FuelCompany/fuel" method="POST" onsubmit="return addFuelChecker()">
                        <label for="fuel_type">Тип: </label><br/>
                        <input type="text" name="fuel_type" placeholder="тип"/><br/>
                        <label for="fuel_name">Название: </label><br/>
                        <input type="text" name="fuel_name" placeholder="название"/><br/>
                        <label for="fuel_price">Цена: </label><br/>
                        <input type="number" name="fuel_price" min="0.00" step="0.01" value="0.00"/><br/>
                        <label for="petrolstation_id">Заправочная станция: </label><br/>
                        <select name="petrolstation_id"><br/>
                            <c:forEach items="${petrolStations}" var="petrolStation">
                                <option value="${petrolStation.getId()}">${petrolStation.getAddress()}</option>
                            </c:forEach>
                        </select><br/>
                        <input type="submit" value="Добавить"/>
                        <p id="add-error"></p>
                    </form>
                </div>
            </div>


        </div>
    </body>
    <script>

    </script>
</html>
