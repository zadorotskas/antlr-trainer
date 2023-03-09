<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Lessons<title>
 </head>
 <body>
   <h1>Lessons:</h1>
   <#list lessons.lessons as lesson>
       <h2>Lesson ${lesson?number}: ${lesson?name}</h2>
   </#list>
 </body>
</html>