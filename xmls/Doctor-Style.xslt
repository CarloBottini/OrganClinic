<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Doctor</title>
      </head>
      <body>
        <h2>Doctor Information</h2>
        <table border="1" cellpadding="5" cellspacing="0">
          <tr>
            <th>Name</th>
            <td><xsl:value-of select="name"/></td>
          </tr>
          <tr>
            <th>Date of Birth</th>
            <td><xsl:value-of select="dob"/></td>
          </tr>
          <tr>
            <th>Gender</th>
            <td><xsl:value-of select="@gender"/></td>
          </tr>
          <tr>
            <th>Email</th>
            <td><xsl:value-of select="email"/></td>
          </tr>
          <tr>
            <th>Telephone</th>
            <td><xsl:value-of select="telephone"/></td>
          </tr>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
