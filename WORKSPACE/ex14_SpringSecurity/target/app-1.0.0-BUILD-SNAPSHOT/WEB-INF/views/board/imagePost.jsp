<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        div{
            width :100px;height : 100px;
            border : 1px solid; margin : 50px;
        }
        #d3{
            width:500px;
            height : 500px;
            
            display:flex;
            justify-content : center;
            align-items : center;
            flex-wrap : wrap;
            
            overflow : auto;
            
        }
    </style>
</head>
<body>
    
   
    <a href="javascript:void(0)" class="uploadBtn">UPLOAD</a>
    <div id="d3"></div>
	
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.6.8/axios.min.js" integrity="sha512-PJa3oQSLWRB7wHZ7GQ/g+qyv6r4mbuhmiDb8BjSFZ8NZ2a42oTtAq5n0ucWAwcQDlikAtkub+tPVCw4np27WCg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
     
		const formData = new FormData();	
    	
        //drag, dragover dragstart,drageend,drop,
        const d3tg =  document.getElementById('d3');
        d3tg.addEventListener('dragover',function(event){
            console.log("dragover!!..");
            event.preventDefault();
        })
        d3tg.addEventListener('drop',function(event){
            //console.log("drop...",event);
            //console.log("drop...",event.dataTransfer.files);
            const files =event.dataTransfer.files;
           
            for(i=0;i<files.length;i++){
                //console.log(files[i]);
                if(files[i].type.startsWith("image/")){
                    //console.log(files[i]);
                    const imgtg = document.createElement('img');    //생성
                    imgtg.src=URL.createObjectURL(files[i]);        //src경로설정
                    imgtg.setAttribute("style","width:200px;height:200px;") //기본스타일지정
                    d3tg.appendChild(imgtg);                        //d3tg연결
                    
                    //formData에 이미지 저장  
                    formData.append("files",files[i])
                    //console.log("files",files[i]);
                }
                
            }
            
   
            
            event.preventDefault();
            
        })
        
        //
        const projectPath = "${pageContext.request.contextPath}";
        
        const uploadBtn = document.querySelector(".uploadBtn");
        uploadBtn.addEventListener("click",function(){
        	console.log("upload btn clicked..");
        	
        	
        	//header
        	const header = {"Content-Type" : "multipart/form-data"};
        	
        	//body==formaata
        	
        	
        	
        	//요청하기 axios.post 사용
        	axios.post(projectPath+"/board/imagePost",formData,header)
        	.then(resp=>{console.log(resp);})
        	.catch(err=>{console.log(err);})
        	
        })

    </script>

	


</body>
</html>