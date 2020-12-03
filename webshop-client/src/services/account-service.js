import axios from "axios";

export default {
    async login(username, password){
        let oath_token = btoa(`${username}:${password}`);
        let output;
        await axios
            .get('http://localhost:4545/v2/authentication/',
                {headers: {'Authentication': `Bearer ${oath_token}`}})
            .then(response => {output = response.data})
            .catch(error => console.log(error))
        return output;
    }
}