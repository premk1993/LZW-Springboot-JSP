<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>LZW Algorithm Encode</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#b3b3ff">

<div class="container">
  <div class="jumbotron">
    <h2 style="text-align:center">Lempel–Ziv–Welch (LZW) Algorithm</h2>      
    <p>The LZW algorithm is a very common compression technique. This algorithm is typically used in GIF and optionally in PDF and TIFF. Unix’s ‘compress’ command, among other uses. It is lossless, meaning no data is lost when compressing. The algorithm is simple to implement and has the potential for very high throughput in hardware implementations. It is the algorithm of the widely used Unix file compression utility compress, and is used in the GIF image format.</p>
  </div>
  
   <div class="panel panel-default">
    <div class="panel-heading">
       <h3>Encoder/Compression</h3>
    </div>
    <div class="panel-body">
       <div class="form-group">
         <form action="/encode">
         
         <input type="hidden" id="email" value=${email} name="email">
         <input type="hidden" id="name" value=${name} name="name">
         <label for="encodetext">
           <h4>Enter the text to Encode:</h4>
         </label>
         <textarea class="form-control" rows="8" id="encodetext" placeholder="Enter the text to Encode" name="encodetext">${encode}</textarea>
         <input type="hidden" id="encodedTag" value=${hasBeenEncodedFlag} name="hasBeenEncodedFlag">
         <div class="btn-group btn-group-justified">
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/encode">Encode here</button>
    </div>
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/encodetofile">Encode to File</button>
    </div>
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/encodetoemail">Encode to Email</button>
    </div>
   </div> </form>
       </div>
    </div>
   </div>
   
    <div class="panel panel-default">
    <div class="panel-heading">
       <h3>Decoder/Decompression</h3>
    </div>
    <div class="panel-body">
       <div class="form-group">
         <form action="/decode">
         
         <input type="hidden" id="email" value=${email} name="email">
         <input type="hidden" id="name" value=${name} name="name">
         <label for="encodetext">
           <h4>Enter the text to Decode:</h4>
         </label>
         <textarea class="form-control" rows="8" id="decodetext" placeholder="Enter the text to Decode" name="decodetext">${decode}</textarea>
         <input type="hidden" id="decodedTag" value=${hasBeenDecodedFlag} name="hasBeenDecodedFlag">
         <div class="btn-group btn-group-justified">
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/decode">Decode here</button>
    </div>
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/decodetofile">Decode to File</button>
    </div>
    <div class="btn-group">
     <button type="submit" class="btn btn-primary" formaction="/decodetoemail">Decode to Email</button>
    </div>
   </div> 
          </form>
       </div>
    </div>
   </div>
        
   
      
</div>


  <!-- Modal -->
  <div class="modal fade" id="DecodeModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <div class="modal-body">
          <h4 class="modal-title">Decoded String</h4>
          <textarea class="form-control" rows="8" id="decodetext" readonly> ${decodedstring} </textarea>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
   <!-- Modal -->
  <div class="modal fade" id="EncodeModal" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <div class="modal-body">
          <h4 class="modal-title">Encoded String</h4>
          <textarea class="form-control" rows="8" id="encodetext" readonly> ${encodedstring} </textarea>
        </div>
        <div class="modal-footer">
          <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

</body>
<script>

var x = document.getElementById('decodedTag').value;
var y = document.getElementById('encodedTag').value;

if(x == "true")
	$("#DecodeModal").modal();


if( y == "true")
	$("#EncodeModal").modal();




</script>
</html>
