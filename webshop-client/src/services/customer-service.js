import axios from "axios";
import Vue from 'vue'

const baseUrl = 'http://localhost:4545/v2/customers/'

export default {
    async getCustomerById(id){
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

    async removeCustomerById(){
        await axios.delete(baseUrl,
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async updateCustomerById(customer){
        await axios.put(baseUrl, JSON.parse(customer),
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {return response})
            .catch(error => (console.log(error)))
    }
}
