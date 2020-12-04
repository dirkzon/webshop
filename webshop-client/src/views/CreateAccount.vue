<template>
  <div>
    <v-layout justify-center>
      <v-card class="mx-8 my-8"
              width="30%">
        <v-responsive :aspect-ratio="16/7" >
          <v-card-title>Create a new account</v-card-title>
          <v-divider></v-divider>
          <v-card-subtitle>your e-mail:</v-card-subtitle>
          <v-form
              v-model="valid">
            <v-text-field
                v-model="email"
                :rules="emailRules"
                outlined
                required
            ></v-text-field>
            <v-card-subtitle>username:</v-card-subtitle>
            <v-text-field
                v-model="username"
                counter="25"
                :rules="userNameRules"
                outlined
                required
            ></v-text-field>
            <v-card-subtitle>password:</v-card-subtitle>
            <v-text-field
                :type= "'password'"
                counter="25"
                v-model="password"
                :rules="passwordRules"
                outlined
                required
            ></v-text-field>
            <v-card-subtitle>account type:</v-card-subtitle>
            <v-select
                v-model="type"
                :items="items"
                :rules="accountTypes">
            </v-select>
            <v-card-text class="red--text">{{errormsg}}</v-card-text>
            <v-btn
                :disabled="!valid"
                @click="CreateAccount()"
                class="mx-2 my-2"
                color="secondary">
              <v-icon dark>
                add
              </v-icon>
              Create my new account
            </v-btn>
          </v-form>
        </v-responsive>
      </v-card>
    </v-layout>
  </div>
</template>

<script>
import accountService from "@/services/account-service";

export default {
  name: "CreateAccount",
  data(){
    return{
      username: "",
      userNameRules: [
        v => !!v || 'Name is required' ,v => v.length <= 25, v => v.length >= 4
      ],
      password: "",
      passwordRules:[
        v => !!v || 'Password is required',v => v.length <= 25, v => v.length >= 6
      ],
      email: "",
      emailRules:[
        v => !!v || 'Email is required',v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      type: "",
      accountTypes:[
          v => !!v || 'Item is required'
      ],
      valid: false,
      items: [
          'Customer',
          'Retailer'
      ],
      errormsg:"",
    }
  },
  methods:{
    CreateAccount: async function(){
      let newUser = JSON.stringify({
        account: {
          email: this.email,
          password: this.password,
          role: this.type,
          username: this.username
        },
        avatar:{
          url: 'https://cdn.jpegmini.com/user/images/slider_puffin_jpegmini_mobile.jpg'
        }
      })
      await accountService.createAccount(newUser, this.type)
      await this.$router.push('/')
    },
  }
}
</script>

<style scoped>
.v-card{
  padding: 40px;
}
</style>