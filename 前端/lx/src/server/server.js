const express = require('express');

const bodyParser = require('body-parser');
/*POST数据的解析需要有中间件先解析一层
body.urlencoded会给req加上一个body属性
req.body本来就是不存在的
加上之后后面才可以正常输出*/
let app = express();
app.use(bodyParser.urlencoded({extended: false}));

app.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With,Authorization");/*加自定义Authorization请求头*/
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", ' 3.2.1');
  res.header("Access-Control-Max-Age", '1728000')/*防止OPTIONS预请求*/
  res.header("Content-Type", "application/json;charset=utf-8");
  next()
});

const qs = require('qs')

/*访问图片*/
app.get('/img/*', function (req, res) {
  console.log(req.url)
  res.sendFile(__dirname + "/" + req.url);
  console.log("Request for " + req.url + " received.");
})

/*图片上传*/
let multiparty = require("multiparty")
app.post('/upload/headImage', function (req, res, next) {
  let form = new multiparty.Form();
  form.uploadDir = "./img";
  form.parse(req, function (err, fields, files) {  //其中fields表示你提交的表单数据对象，files表示你提交的文件对象
    console.log(req);
    console.log(files);
    if (err) {
      res.json({
        status: "1",
        msg: "上传失败！" + err
      });
    } else {

      res.json({
        status: "0",
        msg: "上传成功！",
        imgSrc: files,
        data: 'http://localhost:3001/img/' + files.file[0].path.replace('img\\', '')
      });
    }
  })

});

app.listen(3001)


