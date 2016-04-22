<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html xmlns="http://www.w3.org/1999/xhtml">
      <body>
	<h2>Products</h2>
	<table border="1">
	  <tr bgcolor="green">
	  		<th>ID</th>
            <th>Name</th>
            <th>SourceURL</th>
            <th>Description</th>
            <th>minPrice</th>
         
	  </tr>
	  <xsl:for-each select="//items/product">
	    <tr>
	      
		<td><xsl:value-of select="@id"/></td>
		<td><xsl:value-of select="name"/></td>
		<td><a href = "{productOffersURL}"><img width="100" height="100" src="{images/image/sourceURL}" class="CalloutRightPhoto"/></a></td>
		<td><xsl:value-of select="fullDescription"/></td>
		<td><xsl:value-of select="minPrice"/></td>	      
	      
	    </tr>
	  </xsl:for-each>
	</table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>