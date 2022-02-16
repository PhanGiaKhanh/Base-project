<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category list</title>
</head>
<body>
    <h1>Product list by CategoryID</h1>
    <table class="table table-striped table-dark" border="1" >
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Category Id</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <th scope="row">${product.productId}</th>
                    <td>${product.productName}</td>
                    <td>${product.categoryId}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>
                        <a href="#">Edit</a>
                        <a href="#">Delete</a>
                    </td>
                  </tr>
            </c:forEach>
        </tbody>
      </table>
</body>
</html>