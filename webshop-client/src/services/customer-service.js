import axios from "axios";

const baseUrl = 'http://localhost:4545/v2/customers/'

export default {
    async getCustomerById(id){
        await axios.get(baseUrl + id)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async removeCustomerById(id){
        await axios.delete(baseUrl + id)
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async updateCustomerById(id, customer){
        await axios.put(baseUrl + id, JSON.parse(customer))
            .then(response => {return response})
            .catch(error => (console.log(error)))
    }
}