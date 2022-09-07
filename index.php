<?php

$link = mysqli_connect('host', 'user', 'password', 'db');

if (!$link) {
    die('Could not connect:');
}

mysqli_select_db($link, "db");

$q = mysqli_query($link, "SELECT * FROM movies ORDER BY id;");

echo "<!doctype html>";
echo "<html lang=\"en\">";
echo "<head>";
echo "  <meta charset=\"utf-8\">";
echo "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">";
echo "  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">";
echo "  <title>Trabalho Cloud Architecture</title>";
echo "</head>";
echo "<body>";
echo "<h3>Trabalho Cloud Architecture - Lista</h3>";
echo "<table class=\"table table-striped\">";
echo "  <thead>";
echo "      <tr>";
echo "          <th scope=\"col\">#</th>";
echo "          <th scope=\"col\">Title</th>";
echo "          <th scope=\"col\">Description</th>";
echo "      </tr>";
echo "  </thead>";
echo "  <tbody>";

for ($c = 0; $c < mysqli_num_rows($q); $c++) {
    echo "<tr>";
    $f = mysqli_fetch_array($q);
    echo "  <th scope=\"row\">$f[0]</th>";
    echo "  <td>$f[1]</td>";
    echo "  <td>$f[2]</td>";
    echo "</tr>";
}
echo "  </tbody>";
echo "</table>";
echo "</body>";
echo "</html>";
