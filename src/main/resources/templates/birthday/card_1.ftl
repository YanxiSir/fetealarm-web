<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <title>Document</title>
</head>
<style type="text/css">
    .card {
        height: 180px;
        line-height: 30px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal;
        display:-webkit-box;
        -webkit-box-orient:vertical;
        -webkit-line-clamp:6;
        word-break:break-all;
        margin-left:10px;
        margin-right: 10px;
    };
    #demo_121 .n-invalid {border: 1px solid #f00;}
</style>
<body>
<div class="container" id="cardDisplay" style="padding-top: 20px;">
    <div class='col-md-6'> <div class='thumbnail' style='box-shadow:0 2px 20px #888888;border-radius:25px;background-image:url( ${imageUrl} );'>
        <div style='padding-left: 10px; padding-top: 15px;'><h3>To ${toName}</h3></div>
        <div class='card'>${content}</div>
        <div style='text-align: right; padding-bottom: 15px; padding-right: 20px;'>
            <h3>${fromName}</h3>
        </div> </div> </div>
</div>
<#--<img src="${imageUrl}">-->
<div class="container" style="height: 200px;">

</div>

</body>
</html>