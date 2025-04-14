<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
</head>
<body>
    <h1>Welcome to Student Portal</h1>
    <h2>Enter Attendance Details</h2>
    <form action="AttendanceServlet" method="post">
        Student Name: <input type="text" name="student_name"><br>
        Date: <input type="date" name="date"><br>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>