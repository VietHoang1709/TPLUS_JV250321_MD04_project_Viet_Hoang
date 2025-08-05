<%@ page import="controller.CategoryController" %><%--
  Created by IntelliJ IDEA.
  User: leviethoang
  Date: 2025/08/04
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Display Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background-color: #f9f9f9;
        }

        h3 {
            color: #333;
            text-align: center;
        }

        table {
            width: 90%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e9f5ff;
        }

        a {
            text-decoration: none;
            color: #007BFF;
            padding: 5px 10px;
            border: 1px solid #007BFF;
            border-radius: 5px;
            transition: 0.3s;
            margin: 0 3px;
        }

        a:hover {
            background-color: #007BFF;
            color: white;
        }

        .create-link {
            display: block;
            width: fit-content;
            margin: 20px auto;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h3>Danh mục sản phẩm</h3>
    <table border = 1>
        <thead>
        <tr>
            <th>No</th>
            <th>Category Id</th>
            <th>Category Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                    <td>${category.categoryDescription}</td>
                    <td>${category.status}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/CategoryController?action=Update&categoryId=${category.categoryId}">Edit</a>
                        <a href="<%=request.getContextPath()%>/CategoryController?action=Delete&categoryId=${category.categoryId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/View/newCategory.jsp">Create new Category...</a>
</body>
</html>
