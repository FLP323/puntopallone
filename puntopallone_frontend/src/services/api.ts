import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/rest',
    withCredentials: true,  // invia sempre il cookie JSESSIONID
    headers: {
        'Content-Type': 'application/json',
    },
});

export default api;