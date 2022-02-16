
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign</title>
    <style>
    	.error {
    		color:red;
    	}
    </style>
</head>
<body>
    <p>Do you really want to assign product: 
        <strong>${product.productName}

        </strong> to other category ?
    </p>
    <form:form method="POST"
        action="/products/updateProduct/${product.productId}"  
        modelAttribute="product">
        <label for="productName">Product name</label><br>
        <form:input path="productName" placeholder="Enter Product's name" id="productName"/>  <br>
        <form:errors path="productName" cssClass="error"/> <br>
        <label for="category">Category ID</label> <br>
        <form:select path="categoryId" id="category">
            <c:forEach var="category" items="${categories}">
                <form:option value="${category.categoryId}">
                    ${category.categoryName}
                </form:option>
            </c:forEach>                        
        </form:select><br>
        <label for="price">Price</label><br>
        <form:input path="price" placeholder="Enter Product's price" id="price"/><br>
        <form:errors path="price" cssClass="error"/> <br>
        <label for="description">Description</label><br>
        <form:input path="description" placeholder="Enter Product's description" id="description"/><br>
        <form:errors path="description" cssClass="error"/> <br>
        <input type="submit" value="Update">
    </form:form>
</body>
</html>