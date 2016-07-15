<%--
  Created by IntelliJ IDEA.
  User: florinani
  Date: 12/07/2016
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:url var="cssUrl" value="/resources/style/PaginatorStyle.css"></c:url>
    <link rel="stylesheet" type="text/css" href="${cssUrl}">

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <title>Products</title>
</head>
<body>

                <c:if test="${not empty products}">

                    <div id="container">
                            <c:forEach var="product" items="${products}">
                                <div class="square" >
                                    <ul>
                                        <li>Code: ${product.code} </li>
                                        <li>Name: ${product.name} </li>
                                        <li>Description: ${product.description} </li>
                                        <li>Price: ${product.price}</li>
                                        <li>StockLevel: ${product.stockLevel}</li>

                                        <br>
                                        <c:if test="${product.stockLevel gt 0}">
                                            <span class="label label-success">In stock!</span>
                                        </c:if>
                                        <c:if test="${product.stockLevel lt 1}">
                                            <span class="label label-warning">Out of stock!</span>
                                        </c:if>
                                    </ul>
                                </div>
                            </c:forEach>
                    </div>

                </c:if>

    <c:set var="nrPages" value="${productSize div 9}"/>
    <c:set var="dateParts" value="${fn:split(nrPages, '.')}" />
    <c:set var="nrPagesInt" value="${dateParts[0]}" ></c:set>

    <c:if test="${dateParts[1]!=\"0\"}">
        <c:set var="nrPagesInt" value="${nrPagesInt+1}"/>
    </c:if>

    <c:if test="${currentPage>1}">
        <a href= "/mvc/products?page=${1}" methods="GET" >1</a>
    </c:if>
    <c:if test="${currentPage-1 >1}">
        ...
    </c:if>
    <c:if test="${currentPage!=1 && currentPage-1!=1}">
        <a href= "/mvc/products?page=${currentPage-1}" methods="GET" >${currentPage-1}</a>
    </c:if>
    <a href= "/mvc/products?page=${currentPage}" methods="GET" >${currentPage}</a>

    <c:if test="${currentPage <nrPagesInt-1}">
        <a href= "/mvc/products?page=${currentPage+1}" methods="GET" >${currentPage+1}</a>
    </c:if>
    <c:if test="${currentPage+1 <nrPagesInt-1}">
        ...
    </c:if>
    <c:if test="${currentPage!=nrPagesInt}">
        <a href= "/mvc/products?page=${nrPagesInt}" methods="GET">${nrPagesInt}</a>
    </c:if>


</body>
</html>
