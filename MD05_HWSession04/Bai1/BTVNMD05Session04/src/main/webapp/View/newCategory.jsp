<%--
  Created by IntelliJ IDEA.
  User: leviethoang
  Date: 2025/08/04
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Category </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 40px;
        }

        h3 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: auto;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        .radio-group {
            margin-bottom: 15px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h3>Create new Category</h3>
    <form action="<%=request.getContextPath()%>/CategoryController?action=Create" method="post">
        <label for="categoryname">Category Name</label>
        <input type="text" id="categoryname" name="categoryName"><br/>
        <label for="description">Category Description</label>
        <input type="text" id="description" name="categoryDescription"><br/>
        <label for="Active">Status</label>
        <input type="radio" id="active" name="status" value="true" checked><label for="active">Active</label>
        <input type="radio" id="inActive" name="status" value="false" ><label for="inActive">InActive</label>
        <input type="submit" value="Create">
        
    </form>
</body>
</html>
