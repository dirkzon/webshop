import axios from "axios";
import Vue from 'vue'

const baseUrl = 'http://localhost:4545/v2/retailers/'

export default {
    async getRetailerById(id){
        await axios.get(baseUrl + id)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async getMe(){
        let output;
        await axios.get(baseUrl + "me",
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output =  response.data})
            .catch(error => (console.log(error)))
        return output;
    },

    async removeRetailerById(id){
        await axios.delete(baseUrl + id,
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async updateRetailerById(retailer){
        await axios.put(baseUrl, JSON.parse(retailer),
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async getAllProducts(id){
        let output;
        await axios.get(baseUrl + id + '/catalog',
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output =  response.data})
            .catch(error => (console.log(error)))
        return output;
    },

    async createProduct(product){
        let output;
        await axios.post(baseUrl + 'catalog', JSON.parse(product),
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output = response.data})
            .catch(error => (console.log(error)))
        return output;
    }
}
