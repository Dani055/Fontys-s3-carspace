import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/'
});

instance.interceptors.request.use(function (config) {
    const token = window.sessionStorage.getItem("tkn");
    if(token !== null && token !== undefined && token !== ''){
        config.headers.Authorization =  "Bearer " + token;
    }

    return config;
});

instance.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  }, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  });
export default instance;