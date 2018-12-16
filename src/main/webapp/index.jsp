<%@ page import ="java.util.*" %>
<%@ page import ="com.validity.businessLogic.*" %>

<!DOCTYPE html>
<html lang="en" xmlns:jsp="http://www.w3.org/2001/XMLSchema">
<head>
    <meta charset="UTF-8">
    <title>Find Duplicates</title>
</head>
<body>
<h1>
    Duplicates
</h1>
<%
    DuplicateFinder finder = new DuplicateFinder();
    DuplicateDisplay dupeDisplay= finder.findDupes();
    out.println("<br>Potential Duplicates <br><br>");%>

<ul>
<%
    if(dupeDisplay != null){
        for(int i=0;i<dupeDisplay.getDuplicates().size();i++){
           out.println("<li> " + dupeDisplay.getDuplicates().get(i) + "</li>");
        }
    }
%>
</ul>

<ul>
<%
    out.println("<br>Non Duplicates <br><br>");
    if(dupeDisplay != null){
        for(int i=0;i<dupeDisplay.getSingles().size();i++){
           out.println("<li> " + dupeDisplay.getSingles().get(i) + "</li>");
        }
    }
%>
</ul>
</body>
</html>