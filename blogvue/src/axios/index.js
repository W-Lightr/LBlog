import axios from 'axios'



const http = axios.create({
    baseURL: 'http://localhost',
    timeout: 2000,
})

try {
    token = window.localStorage.getItem('token');
} catch (e) { }

http.interceptors.request.use(function (config) {
    config.headers.Authorization = window.localStorage.getItem("token");
    return config;
}, function (error) {
    console.log("请求失败!");
});
http.interceptors.response.use(function (response) {
    let code = response.data.code
    if (code == 400 || code == 401 || code == 402) {
        ElMessage.error(response.data.msg)
    }
    return response;
}, function (error) {
    console.log("请求错误!");
});


export default {
    http
}