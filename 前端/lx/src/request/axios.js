import qs from 'qs'/*格式处理*/
const axios = require('axios');

export const post = (url, param, callback) => {
  axios.post('http://localhost:8010'+url,qs.stringify(param)).then((res) => {
    callback(res)
  });
}

