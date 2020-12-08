import axios from "axios";

const baseUrl = 'http://localhost:4545/v2'

export default {
    async login(username, password){
        let oath_token = btoa(`${username}:${password}`);
        let output;
        await axios
            .get(baseUrl + '/authentication/',
                {headers: {'Authentication': `Bearer ${oath_token}`}})
            .then(response => {output = response.data})
            .catch(error => console.log(error))
        return output;
    },

    async createAccount(newUser, role){
        let url;
        if(role == 'Retailer'){
            url = baseUrl + '/retailers'
        }else{
            url = baseUrl + '/customers'
        }
        await axios
            .post(url, JSON.parse(newUser))
            .then(response => {return response})
            .catch(error => console.log(error))
    }
}