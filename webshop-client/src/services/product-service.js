import axios from "axios";
import Vue from 'vue'

const baseUrl = 'http://localhost:4545/v2/products'

export default {
    async getProductById(id){
        let output;
        await axios
            .get(baseUrl + id,
                {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output = response.data})
            .catch(error => console.log(error))
        return output;
    },

    async removeProductById(id){
        await axios
            .delete(baseUrl + id)
            .then()
            .catch(error => (console.log(error)))
    },

    async updateProductById(id, product){
        await axios
            .put(baseUrl + id, JSON.parse(product))
            .then(response => {return response.data})
            .catch(error => (console.log(error)))
    },

    async createReviewOnProductById(id, review){
        await axios
            .post(baseUrl + id + '/reviews', JSON.parse(review),
                {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {return response})
            .catch(error => (console.log(error)))
    },

    async browseProducts(broseVars){
        let output;
        await axios
            .put(baseUrl + '/browse', JSON.parse(broseVars),
                {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output =  response.data})
            .catch(error => (console.log(error)))
        return output;
    }
}