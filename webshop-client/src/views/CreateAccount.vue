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
              v-model="valid"
              data-cy="createAccountForm">
            <v-text-field
                v-model="email"
                :rules="emailRules"
                outlined
                required
                data-cy="email"
            ></v-text-field>
            <v-card-subtitle>username:</v-card-subtitle>
            <v-text-field
                v-model="username"
                counter="25"
                :rules="requiredFieldRules"
                outlined
                required
                data-cy="username"
            ></v-text-field>
            <v-card-subtitle>password:</v-card-subtitle>
            <v-text-field
                :type= "'password'"
                counter="25"
                v-model="password"
                :rules="requiredFieldRules"
                outlined
                required
                data-cy="password"
            ></v-text-field>
            <v-card-subtitle>account type:</v-card-subtitle>
            <v-select
                required
                outlined
                v-model="type"
                :items="items"
                data-cy="accountType">
            </v-select>
            <div v-if='type == "Customer"'>
              <v-card-title>address:</v-card-title>
              <v-divider></v-divider>
              <v-card-subtitle>country:</v-card-subtitle>
              <v-text-field
                  v-model="countryCode"
                  :rules="requiredFieldRules"
                  outlined
                  required
                  data-cy="country"
              ></v-text-field>
              <v-card-subtitle>Street name:</v-card-subtitle>
              <v-text-field
                  v-model="streetName"
                  :rules="requiredFieldRules"
                  outlined
                  required
                  data-cy="streetName"
              ></v-text-field>
              <v-card-subtitle>House number:</v-card-subtitle>
              <v-text-field
                  v-model="houseNumber"
                  type="number"
                  :rules="requiredNumberRules"
                  outlined
                  required
                  data-cy="houseNumber"
              ></v-text-field>
            </div>
            <v-card-text class="red--text">{{errormsg}}</v-card-text>
            <v-btn
                :disabled="!valid"
                @click="CreateAccount()"
                class="mx-2 my-2"
                color="secondary"
                data-cy="createAccount">
              <v-icon dark>
                done
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
import accountService from "@/services/account-service"

export default {

  name: "CreateAccount",
  data(){
    return{
      username: "",
      requiredFieldRules: [
        v => !!v || 'Field is required' ,v => v.length <= 25, v => v.length >= 4
      ],
      requiredNumberRules: [
        v => !!v || 'Field is required'
      ],
      password: "",
      email: "",
      emailRules:[
        v => !!v || 'Email is required',v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      type: "Customer",
      valid: false,
      items: [
          'Customer',
          'Retailer'
      ],
      countryCode:'',
      streetName:'',
      houseNumber:'',
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
        address: {
          country: this.countryCode,
          streetName: this.streetName,
          houseNumber: this.houseNumber
        }
      })
      await accountService.createAccount(newUser, this.type)
      await this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.v-card{
  padding: 40px;
}
</style>
