<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="today" value="<%=new Date() %>" />

<h1>오늘 날짜 시간 : ${today }</h1>
<h1>오늘 날짜 시간 : <fmt:formatDate value="${today }" pattern="yyyy-MM-dd HH:mm:ss"/></h1>
