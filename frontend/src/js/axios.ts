import axios from 'axios';

const baseUrl = import.meta.env.VITE_API_SERVER_URL

const instance = axios.create({
    baseURL: baseUrl,
    withCredentials: true,
    headers: {'Content-Type': 'application/json'}
});

instance.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            instance.get("/logout")
                .then(_ => {
                    window.location.href = '/login';
                })
        }

        return Promise.reject(error);
    }
);

export default instance;
