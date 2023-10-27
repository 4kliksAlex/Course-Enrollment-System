import { ElMessage } from "element-plus";

const baseURL = 'http://localhost:8080'

function generateUrl(url, params){
    url = baseURL + url + '?';
    if(params){
        for(let param in params){
            url = url + param + '=' + params[param] + '&';
        }
    }
    return url
}

export default {
    async get(url, params){
        url = generateUrl(url, params)
        return fetch(url, {
            mode: 'cors',
        }).then(res => res.json()).then(data => {
            if(data.status !== 200 && data.message){
                ElMessage.warning(data.message);
            }
            return data
        })
    },
    async post(url, params){
        url = generateUrl(url, params)
        return fetch(url, {method: 'POST'}).then(res => res.json()).then(data => {
            if(data.status !== 200 && data.message){
                ElMessage.warning(data.message);
            }
            return data
        })
    }
}
