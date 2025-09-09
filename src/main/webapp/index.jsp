<html>
<head><title>Payment App</title></head>
<body>
    <h2>Make a Payment</h2>
    <form action="pay" method="post">
        User ID: <input type="text" name="user_id" required/><br/>
        Amount: <input type="number" step="0.01" name="amount" required/><br/>
        <input type="submit" value="Pay"/>
    </form>
</body>
</html>
