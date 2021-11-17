<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>회원 목록 조회 - 뷰</title>
  </head>
  <body>
  <a href="/index.html">메인</a>
  <table>

    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>

    <tbody>
    <!--JSP에서 FOR EACH구문 쓰기 화면을 렌더링 하는데 특화된 기능 taglib를 import에서 가져온다.-->
    <!--여기서 가져온 members는 컨트롤러에서 MODEL에 담았던 request.setAttribute("members", members); 이 코드의 members이다-->
    <c:forEach var="item" items="${members}">
      <tr>
        <td>${item.id}</td>
        <td>${item.username}</td>
        <td>${item.age}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

  </body>
</html>