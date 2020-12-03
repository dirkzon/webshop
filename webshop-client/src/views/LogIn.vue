<template>
  <div>
    <v-layout justify-center>
      <v-card class="mx-8 my-8"
              width="30%">
        <v-responsive :aspect-ratio="16/7" >
          <v-card-title>log in</v-card-title>
          <v-divider></v-divider>
          <v-card-subtitle>username or e-mail:</v-card-subtitle>
          <v-form
              v-model="valid">
          <v-text-field
              v-model="username"
              :rules="userNameRules"
              outlined
              required
          ></v-text-field>
          <v-card-subtitle>password:</v-card-subtitle>
          <v-text-field
              :type= "'password'"
              v-model="password"
              :rules="passwordRules"
              outlined
              required
          ></v-text-field>
          <v-card-text class="red--text">{{errormsg}}</v-card-text>
          <v-btn
              :disabled="!valid"
              @click="LogIn()"
              class="mx-2 my-2"
              color="secondary">
            <v-icon dark>
              login
            </v-icon>
            Log in
          </v-btn>
          <v-btn
              @click="test()"
              class="mx-2 my-2"
              color="#cdc9c3">
            <v-icon dark>
              add
            </v-icon>
            Create account
          </v-btn>
          </v-form>
        </v-responsive>
      </v-card>
    </v-layout>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "LogIn",
  data(){
    return{
      username: "",
      userNameRules: [
        v => !!v || 'Name is required'
      ],
      password: "",
      passwordRules:[
          v => !!v || 'Password is required'
      ],
      valid: false,
      errormsg:"",
    }
  },
  methods:{
    LogIn: function () {
      let oath_token = btoa(`${this.username}:${this.password}`);
      axios
          .get('http://localhost:4545/v2/authentication/', {
            headers: {'Authentication': `Bearer ${oath_token}`}
          })
          .then(response => (axios.defaults.headers.common["Authorization"] = `${response.data.token_type} ${response.data.access_token}`));
          router.push("/")
          .catch(error => this.errormsg = `Could not log in: ${error.response.statusText}`)
    }
  }
}
</script>

<style scoped>
.v-card{
  padding: 40px;
}
</style>