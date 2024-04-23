<%--
  Created by IntelliJ IDEA.
  User: sc_tjoeun
  Date: 2024-04-23
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hello, world! </title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<!-- 부트스트랩의 container, row 적용 -->
<div class="container-fluid">
    <div class="row">
        <!-- 기존의 h1 태그(header)가 있던 위치 -->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-link active" aria-current="page" href="#">home</a>
                                <a class="nav-link" href="#">Features</a>
                                <a class="nav-link" href="#">Pricing</a>
                                <a class="nav-link" href="#">Disabled</a>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- header end -->

        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <form action="/todo/modify" method="post">
                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control"
                             value=<c:out value="${dto.tno}" /> readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control"
                                   value='<c:out value="${dto.title}" />' >
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" class="form-control"
                                   value='<c:out value="${dto.dueDate}" />' >
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="writer" class="form-control"
                                   value='<c:out value="${dto.writer}" />' readonly>
                        </div>
                        <div class="input-group mb-3">
                            <label class="form-check-label">
                                Finished &nbsp;
                            </label>
                            <input type="checkbox" name="finished" class="form-check-input"
                                  ${dto.finished ? "checked" : ""} >
                        </div>
                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-danger">Remove</button>
                                <button type="button" class="btn btn-primary">Modify</button>
                                <button type="button" class="btn btn-secondary">List</button>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        const serverValidResult = {}

        <c:forEach items="${errors}" var="error">
        serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
        </c:forEach>

        console.log(serverValidResult)

    </script>
    <script>

        const formObj = document.querySelector("form")

        document.querySelector(".btn-danger").addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            formObj.action = "/todo/remove";
            formObj.method = "post";
            formObj.submit();

        }, false);

        document.querySelector(".btn-primary").addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            formObj.action = "/todo/modify";
            formObj.method = "post";
            formObj.submit();
        }, false);

        document.querySelector(".btn-secondary").addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            self.location = "/todo/list";
        }, false);
    </script>
    <div class="row footer">
        <!--<h1>Footer</h1>-->

        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1 ">
                <p class="text-center text-muted">footer</p>
            </footer>
        </div>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>