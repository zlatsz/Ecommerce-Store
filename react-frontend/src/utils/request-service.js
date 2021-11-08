import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api/";

class DataService {
    getCategories() {
        return axios.get(API_BASE_URL);
    }

    login(username, password, email) {
        return axios
            .post(API_BASE_URL + "login", {
                username,
                password,
                email
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username, email, password, confirmPassword) {
        return axios.post(API_BASE_URL + "register", {
            username,
            email,
            password,
            confirmPassword
        });
    }

}

export default new DataService();