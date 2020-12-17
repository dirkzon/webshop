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

    async updateRetailerById(id,retailer ){
        await axios.put(baseUrl + id, JSON.parse(retailer))
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

    async createProduct(id, product){
        await axios.post(baseUrl + id + '/catalog', product)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    }
}
