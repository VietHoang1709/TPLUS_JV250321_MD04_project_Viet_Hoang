<%--
  Created by IntelliJ IDEA.
  User: leviethoang
  Date: 2025/08/04
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 40px;
        }

        h3 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 25px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="radio"] {
            margin-right: 5px;
            margin-left: 10px;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #28a745;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>

<body>
<h3>Update Category</h3>
<form action="<%=request.getContextPath()%>/CategoryController?action=Update" method="post">
    <label for="categoryId">Category ID</label>
    <input type="text" id="categoryId" name="categoryId"  value="${category.categoryId}" readonly><br/>
    <label for="categoryname">Category Name</label>
    <input type="text" id="categoryname" name="categoryName" value="${category.categoryName}"><br/>
    <label for="description">Category Description</label>
    <input type="text" id="description" name="categoryDescription" value="${category.categoryDescription }"><br/>
    <label for="Active">Status</label>
    <input type="radio" id="active" name="status" value="true" checked><label for="active">Active</label>
    <input type="radio" id="inActive" name="status" value="false" ><label for="inActive">InActive</label>
    <input type="submit" value="Update">
</form>
</body>
</html>