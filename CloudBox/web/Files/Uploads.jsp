<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="color: green;">
<%
 try
            {
                if(!request.getAttribute("success_upload").equals(null))
                {
                    out.println("File Uploaded Successfully. Upload another?");
                }
            }
catch(Exception ex){}
 try
            {
                if(!request.getAttribute("failure_upload").equals(null))
                {
                    out.println("Executable files cannot be Uploaded!");
                }
            }
catch(Exception ex){}
 try
            {
                if(!request.getAttribute("nothing_upload").equals(null))
                {
                    out.println("Select a file first");
                }
            }
catch(Exception ex){}
%>
</div>

<form method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Select File to Upload:</td>
            <td><input type="file" name="fileName"></td>
        </tr>
        
        <tr>
            <td><input type="submit" value="Upload" onclick="form.action='UploadDownloadFileServlet'"></td>
        </tr>
    </table>
</form>