<%@page import="com.models.PetrolStation"%>
<%@page import="java.util.List"%>
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

            List<Company> beans = (List<Company>) request.getAttribute("beans");
            PetrolStation petrolStation = (PetrolStation) request.getAttribute("petrolstation");
        %>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <c:forEach items="${beans}" var="company">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col-sm-12 name">
                                        Название :<h3>${company.getName()}</h3>
                                    </div>
                                </div>
                               
                                <div class="row">
                                    <c:forEach items="${company.getPetrolStations()}" var="petrolstation">
                                        <div class="col-sm-12 info">
                                            <div class="row">
                                                <div class="col-sm-12 company-address">
                                                    Адрес: ${petrolstation.getAddress()}
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    Время работы: ${petrolstation.getStartTime()}-${petrolstation.getFinishTime()}
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <a href="/FuelCompany/petrolstations?id=${company.getId()}&&delete=${petrolstation.getId()}">Удалить</a>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12 update-form" >
                                                    <c:choose>
                                                        <c:when test="${petrolStation!=null && petrolStation.getId()==petrolstation.getId()}">

                                                            <form action="/FuelCompany/petrolstations" method="POST" name="update-petrolstation" onsubmit="return updatePetrolStationChecker()">
                                                                <input type="text" name="address" placeholder="${petrolStation.getAddress()}" value="${petrolStation.getAddress()}"/>
                                                                <input type="text" name="start_time" placeholder="${petrolStation.getStartTime()}" value="${petrolStation.getStartTime()}"/>
                                                                <input type="text" name="finish_time" placeholder="${petrolStation.getFinishTime()}" value="${petrolStation.getFinishTime()}"/>
                                                                <input type="hidden" name="company_id" value="${company.getId()}"/>
                                                                <input type="hidden" name="id" value="${petrolStation.getId()}"/>
                                                                <input type="submit" value="Редактировать">
                                                            </form>
                                                            

                                                        </c:when>    
                                                        <c:otherwise>
                                                            <a href="/FuelCompany/petrolstations?id=${company.getId()}&&update=${petrolstation.getId()}">Редактировать</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="col-sm-12">
                                                    <p id="petrolstation-update-error"></p>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>

                            </div>
                            <div class="col-md-4">
                                 <div class="row">
                                    <div class="col-sm-12">
                                        <h3> <span> Добавить заправочную станцию </span></h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <form id="add-form-${company.getId()}" class="add-form" name="add-petrolstation-${company.getId()}" action="petrolstations" method="POST" onsubmit="return addPetrolStationChecker(${company.getId()})">
                                            <label for="address">Адрес:</label>
                                            <input type="text" name="address"/>
                                            <label for="start_time">Начало работы:</label>
                                            <input type="text" name="start_time"/>
                                            <label for="finish_time">Конец работы:</label>
                                            <input type="text" name="finish_time"/>
                                            <input type="hidden" name="company_id" value="${company.getId()}"/>
                                            <input type="submit" value="добавить">
                                        </form>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <p id="petrolstation-add-error-${company.getId()}"></p>

                                    </div>
                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
   
</html>
