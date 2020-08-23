# Infrrd_Assignment
 Solution for Infrrd Backend Java interview


The git pull is coded on java with spring boot.
It contains 5 APIs that have the following features-

1. POST - uploadFile - uploads the file specified in 'file' attribute to the server (or local instance) in the 'files' directory.
2. GET - filesNames - returns a list of files that are present in the 'files' directory.
3. GET - file - returns the byte array of the file name specified in 'fileName' attribute of the API request.
4. POST - cloneFile - creates the file data of the file name that is specified if that file exisits in the 'files' directory
5. DELETE - file - deletes the fileName specified if it exists in the 'files' directory 

* Local Swagger URL : http://localhost:8080/swagger-ui.html

* Hosted server Swagger URL : http://ec2-100-25-17-100.compute-1.amazonaws.com:8080/swagger-ui.html 
(Application can be tested with the url too :) )

NOTE : The term 'big file' is very definative for size, I have kept the file limit to 100MB, if there needs to compy for larger files please update the same properties in the application.properties file
