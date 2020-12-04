import axios from "axios";

const baseUrl = 'http://localhost:4545/v2/retailers/'

export default {
    async getRetailerById(id){
        await axios.get(baseUrl + id)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async removeRetailerById(id){
        await axios.delete(baseUrl + id)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async updateRetailerById(id,retailer ){
        await axios.put(baseUrl + id, JSON.parse(retailer))
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async getAllProducts(id){
        await axios.get(baseUrl + id + '/catalog')
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async createProduct(id, product){
        await axios.post(baseUrl + id + '/catalog', product)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    }
}