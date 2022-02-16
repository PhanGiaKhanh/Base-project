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
  <h1>List categories</h1>
    <table class="table table-striped table-dark" border="1" >
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <th scope="row">${category.categoryId}</th>
                    <td>${category.categoryName}</td>
                    <td>${category.description}</td>
                    <td>
                        <a href="/products/${category.categoryId}">Show list products</a>
                    </td>
                  </tr>
            </c:forEach>
        </tbody>
      </table>
      <a href="/products">Back All Products</a>
</body>
</html>