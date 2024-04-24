<%--
  Created by IntelliJ IDEA.
  User: sc_tjoeun
  Date: 2024-04-23
  Time: 오전 10:06
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

        <!-- 검색을 위해서 추가하는 코드 -->
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Search</h5>
                        <form action="/todo/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished" ${pageRequestDTO.finished?"checked":""}> 완료여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
                                <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w")?"checked":""}>작성자
                                <input type="text" name="keyword" class="form-control" placeholder="검색 내용" value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">Search</button>
                                    <button class="btn btn-info clearBtn" type="reset">Clear</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- 게시물이 보이는 코드 영역 -->
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Featured
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Special title treadtment</h5>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Write</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${responseDTO.dtoList}" var="dto">
                            <tr>
                                <th scope="row"><c:out value="${dto.tno}"/></th>
                                <td>
                                    <a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}" class="text-decoration-none">
                                    <c:out value="${dto.title}"/>
                                    </a>
                                </td>
                                <td><c:out value="${dto.writer}"/></td>
                                <td><c:out value="${dto.dueDate}"/></td>
                                <td><c:out value="${dto.finished}"/></td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="float-end">
                            <ul class="pagination flex-wrap">
                                <c:if test="${responseDTO.prev}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                                </li>
                                </c:if>
                                <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                    <li class="page-item ${responseDTO.page == num ? "active": ""}" >
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${responseDTO.next}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                </li>
                                </c:if>
                            </ul>
                        </div>
                        <script>
                            document.querySelector(".pagination").addEventListener("click", function (e) {
                                e.preventDefault();
                                e.stopPropagation();

                                const target = e.target;
                                // console.log(e);
                                // console.log(target);
                                // console.log(target.tagName)
                                if(target.tagName !=='A') {   // A 태그만 작업합니다.
                                    return
                                }
                                const num = target.getAttribute("data-num");

                                const formObj = document.querySelector("form");

                                formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`

                                // console.log(formObj);
                                formObj.submit();

                                <%--self.location = `/todo/list?page=\${num}&${pageRequestDTO.link}`  //백틱(``)을 이용한 템플릿 처리--%>
                                // 백틱은 문자열에 + 를 사용해야 하는 불편을 없애줘요~!~
                            },false)

                            document.querySelector(".clearBtn").addEventListener("click",function (e) {
                                e.preventDefault();
                                e.stopPropagation();

                                self.location = '/todo/list'

                            }, false)

                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
