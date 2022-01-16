<%@page import="java.util.List"%>
<%@page import="com.models.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>companies</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet"  href="./styles/style.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <%
            Company bean = (Company) request.getAttribute("bean");
            List<Company> beans = (List<Company>) request.getAttribute("beans");
        %>
        <div class="container">
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <form class="form" action="/FuelCompany/company" method="POST" name="company-form" class="update-form" onsubmit="return companyChecker()">
                        <div class="row">
                            <div class="col-sm-12">
                                <select onchange="document.location.href = '/FuelCompany/company?id=' + this.value;">
                                    <option value="" ${bean==null?' selected':''} >Добавить компанию</option>
                                    <c:forEach items="${beans}" var="company">
                                        <option value="${company.getId()}" ${company!=null && bean.getId()==company.getId()?' selected':''}>${company.getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <span>Название:</span><br/> <input type="text" name="name" value="${bean==null?"":bean.getName()}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <span>Адрес:</span><br/><input type="text" name="address" value="${bean==null?"":bean.getAddress()}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <span>INN:</span><br/><input type="text" name="inn" value="${bean==null?"":bean.getInn()}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <span>Телефон:</span><br/><input type="text" name="phone" value="${bean==null?"":bean.getPhone()}"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="hidden" name="id" value="${bean==null?"":bean.getId()}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="submit" value="${(bean==null || bean.getId()==-1)?"добавить":"Сохранить изменения"}"/>
                            </div>
                        </div>
  
                    </form>
                    <p id="company-error"></p>
                   <% if (bean != null && bean.getId() != -1) {
                    out.print("<div class=\"company-delete\"><a href=\"/FuelCompany/company?delete=" + bean.getId() + "\">Удалить компанию</a></div>");
                }%>
                </div>
                     <div class="col-sm-4"></div>
            </div>
           

        </div>

    </body>
</html>
