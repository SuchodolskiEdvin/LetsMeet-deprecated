import axios from "axios";

export var api = {
    async get(context, path, params, onSuccess, onError) {
        const successHandler = response => {
            if (typeof onSuccess === "function") {
                onSuccess(response.data);
            }
        }
        const errorHandler = response => {
            if (typeof onSuccess === "function") {
                onError(response);
            }
        }
        axios({
            method: 'get',
            url: process.env.VUE_APP_API_URL + path,
            params: params,
            responseType: 'json'
        })
            .then(successHandler)
            .catch(errorHandler);
    },

    async post(context, path, data, onSuccess, onError) {
        const successHandler = response => {
            if (typeof onSuccess === "function") {
                onSuccess(response.data);
            }
        }
        const errorHandler = response => {
            if (typeof onSuccess === "function") {
                onError((response.message.match(/\d+/g) || []).map(n => parseInt(n))[0]);
            }
        }
        axios({
            method: 'post',
            url: process.env.VUE_APP_API_URL + path,
            data: data,
            responseType: 'json'
        })
            .then(successHandler)
            .catch(errorHandler);
    },

    async put(context, path, data, onSuccess, onError) {
        const successHandler = response => {
            if (typeof onSuccess === "function") {
                onSuccess(response.data);
            }
        }
        const errorHandler = response => {
            if (typeof onSuccess === "function") {
                onError(response);
            }
        }
        axios({
            method: 'put',
            url: process.env.VUE_APP_API_URL + path,
            data: data,
            responseType: 'json'
        })
            .then(successHandler)
            .catch(errorHandler);
    },

    async delete(context, path, params, onSuccess, onError) {
        const successHandler = response => {
            if (typeof onSuccess === "function") {
                onSuccess(response.data);
            }
        }
        const errorHandler = response => {
            if (typeof onSuccess === "function") {
                onError(response);
            }
        }
        axios({
            method: 'delete',
            url: process.env.VUE_APP_API_URL + path,
            params: params,
            responseType: 'json'
        })
            .then(successHandler)
            .catch(errorHandler);
    },
}