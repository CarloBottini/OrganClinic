<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- Plantilla base para transformar el nodo raíz Patient -->
  <xsl:template match="/Patient">
    <html>
      <head>
        <title>Patient Information</title>
        <style>
          table {
            border-collapse: collapse;
            width: 50%;
            font-family: Arial, sans-serif;
          }
          th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
          }
          th {
            background-color: #f2f2f2;
          }
        </style>
      </head>
      <body>
        <h2>Patient Details</h2>
        <table>
          <tr><th>Field</th><th>Value</th></tr>
          <tr>
            <td>Name</td>
            <td><xsl:value-of select="name"/></td>
          </tr>
          <tr>
            <td>Date of Birth</td>
            <td><xsl:value-of select="dob"/></td>
          </tr>
          <tr>
            <td>Gender</td>
            <td><xsl:value-of select="@gender"/></td>
          </tr>
          <tr>
            <td>OrganFailure</td>
            <td><xsl:value-of select="organFailure"/></td>
          </tr>
          <tr>
            <td>Email</td>
            <td><xsl:value-of select="email"/></td>
          </tr>
          <tr>
            <td>Telephone</td>
            <td><xsl:value-of select="telephone"/></td>
          </tr>
          <tr>
            <td>Blood Type</td>
            <td><xsl:value-of select="bloodType"/></td>
          </tr>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
