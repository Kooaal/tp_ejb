
<%@ taglib prefix="my" uri="/WEB-INF/my-taglib.tld" %>
<html>
  <head>
    <title>JSP 2.0 Examples - my example</title>
  </head>
  <body>
    <h1>JSP 2.0 Examples - my example</h1>
    <hr>
    <p>Illustrates a.../p>
    <br>
    <b><u>Result:</u></b><br>
    <my:populate var="myitem"/>
    <table border="1">
        <thead>
	    <td><b>Field</b></td>
	    <td><b>Value</b></td>
	    <td><b>Capitalized</b></td>
	</thead>
	<tr>
	    <td>Title</td>
	    <td>${myitem.title}</td>
	    <td>${my:mycaps(myitem.title)}</td>
	</tr>
	<tr>
	    <td>Author</td>
	    <td>${myitem.author}</td>
	    <td>${my:mycaps(myitem.author)}</td>
	</tr>
	<tr>
	    <td>ISBN</td>
	    <td>${myitem.isbn}</td>
	    <td>${my:mycaps(myitem.isbn)}</td>
	</tr>
    </table>
  </body>
</html>
