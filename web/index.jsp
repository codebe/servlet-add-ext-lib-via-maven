<%--
  Created by IntelliJ IDEA.
  User: Ikhsan
  Date: 6/25/13
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Authentication Application</title>
  </head>
  <body>
    <h1>Welcome to Authentication</h1>
    <form action="/send" method="POST">
        <div class="row">
            <label>Message</label>
            <textarea name="message">

            </textarea>
        </div>
        <div class="row">
            <input type="submit" value="Submit" />
        </div>
    </form>

    <p>
        Plain Message : ${plain},
        Encrypted Message : ${encrypted}
    </p>
  </body>
</html>