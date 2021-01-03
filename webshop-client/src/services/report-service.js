import axios from "axios";
import Vue from 'vue'

const baseUrl = 'http://localhost:4545/v2/reports'

export default {
    async reportReview(report){
        let output;
        await axios.post(baseUrl, JSON.parse(report),
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output = response.data})
            .catch(error => (console.log(error)))
        return output;
    },

    async getAllReportsForRetailer(){
        let output;
        await axios.get(baseUrl + '/all',
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {output = response.data})
            .catch(error => (console.log(error)))
        return output;
    },

    async removeReview(id){
        await axios.delete(baseUrl + '/' + id,
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {console.log(response)})
            .catch(error => (console.log(error)))
    },

    async dismissReport(id){
        await axios.put(baseUrl + '/' + id,null,
            {headers: {'Authorization': Vue.$cookies.get("access_token")}})
            .then(response => {console.log(response)})
            .catch(error => (console.log(error)))
    }
}
