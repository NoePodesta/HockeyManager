
<!DOCTYPE html>
<html>
<head>
<title>HTML5 File Reader | onlyWebPro.com</title>

<style>
.demo_page {
	background: #a9adb0 url(../page_bg_center.jpg) no-repeat center top;
	color: #333;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
.demo_content {
	margin: 0 auto;
	text-align: center;
	width: 468px;
}

.info {
	margin: 15px 0;	
}

</style>
</head>
<body class="demo_page">
<div class="demo_content">
  
  <div class="info">
    <input type="file" id="files" />
    <output id="list"></output>
    <script>
	if (window.FileReader) {
      function handleFileSelect(evt) {
        var files = evt.target.files;
        var f = files[0];
        var reader = new FileReader();
		
          reader.onload = (function(theFile) {
            return function(e) {
              document.getElementById('list').innerHTML = ['<img src="', e.target.result,'" title="', theFile.name, '" width="200"/>'].join('');
            };
          })(f);
    
          reader.readAsDataURL(f);
      }
	 } else {
	  alert('This browser does not support FileReader');
	}
    
      document.getElementById('files').addEventListener('change', handleFileSelect, false);
    </script>
    
  </div>
  </div>
<!-- END demo_content -->
</body>
</html>